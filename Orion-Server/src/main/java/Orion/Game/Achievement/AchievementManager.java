package Orion.Game.Achievement;

import Orion.Api.Server.Game.Achievement.Data.IAchievement;
import Orion.Api.Server.Game.Achievement.Data.IAchievementLevel;
import Orion.Api.Server.Game.Achievement.IAchievementManager;
import Orion.Api.Server.Game.Habbo.Data.Achievement.IHabboAchievementProgress;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Storage.Repository.Achievement.IAchievementRepository;
import Orion.Game.Achievement.Data.Achievement;
import Orion.Game.Achievement.Data.AchievementLevel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import gnu.trove.map.hash.THashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class AchievementManager implements IAchievementManager {
    private final Logger logger = LogManager.getLogger();

    @Inject
    private IAchievementRepository repository;

    private final THashMap<String, IAchievement> achievements;

    public AchievementManager() {
        this.achievements = new THashMap<>();
    }

    @Override
    public void initialize() {
        this.loadAchievements();
    }

    @Override
    public IAchievement getAchievement(String name) {
        return this.achievements.get(name);
    }

    @Override
    public THashMap<String, IAchievement> getAchievements() {
        return this.achievements;
    }

    @Override
    public boolean achievementCompleted(final IHabbo habbo, final IAchievement achievement) {
        final IHabboAchievementProgress progress = habbo.getAchievements().getProgressByAchievement(achievement);

        if(progress == null || progress.getCurrentProgress() < 0) return false;

        final IAchievementLevel level = this.getCurrentLevel(achievement, progress.getCurrentProgress());

        if(level == null) return false;

        final IAchievementLevel nextLevel = achievement.getLevel(level.getLevel() + 1);

        return nextLevel == null && progress.getCurrentProgress() >= level.getProgressNeeded();
    }

    @Override
    public IAchievementLevel getCurrentLevel(IAchievement achievement, int progress) {
        if(progress <= 0) return null;

        IAchievementLevel currentLevel = null;

        for(final IAchievementLevel level : achievement.getLevels().values()) {
            if(progress < level.getProgressNeeded()) continue;

            if(currentLevel != null && currentLevel.getLevel() > level.getLevel()) continue;

            currentLevel = level;
        }

        return currentLevel;
    }

    @Override
    public IAchievementLevel getNextLevel(IAchievement achievement, int currentProgress) {
        for (final IAchievementLevel level : achievement.getLevels().values()) {
            if (level.getLevel() != (currentProgress + 1)) continue;

            return level;
        }

        return null;
    }

    private void loadAchievements() {
        this.repository.loadAllAchievements(result -> {
            if(result == null) return;

            try {
                if(this.achievements.containsKey(result.getString("name"))) {
                    this.achievements.get(result.getString("name")).addLevel(new AchievementLevel(result));
                    return;
                }

                this.achievements.putIfAbsent(result.getString("name"), new Achievement(result));
            } catch (Exception e) {
                this.logger.error(STR."Error loading an achievement: \{e.getMessage()}");
            }
        });

        final int levelsCount = this.achievements.values().stream().mapToInt(IAchievement::getLevelsCount).sum();

        this.logger.debug(STR."[\{this.achievements.size()}] achievements with [\{levelsCount}] levels loaded successfully.");
    }
}
