package Orion.Game.Achievement;

import Orion.Api.Server.Game.Achievement.Data.IAchievement;
import Orion.Api.Server.Game.Achievement.Data.IAchievementLevel;
import Orion.Api.Server.Game.Achievement.IAchievementManager;
import Orion.Api.Server.Game.Habbo.Data.Achievement.IHabboAchievementProgress;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Storage.Repository.Achievement.IAchievementRepository;
import Orion.Game.Achievement.Data.Achievement;
import Orion.Game.Achievement.Data.AchievementLevel;
import Orion.Game.Habbo.Data.Achievement.HabboAchievementProgress;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import gnu.trove.map.hash.THashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class AchievementManager implements IAchievementManager {
    private final Logger logger = LogManager.getLogger();

    private final IHabboAchievementProgress emptyAchievementProgress;

    @Inject
    private IAchievementRepository repository;

    private final THashMap<String, IAchievement> achievements;

    public AchievementManager() {
        this.achievements = new THashMap<>();

        this.emptyAchievementProgress = new HabboAchievementProgress(0, null, null);
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
    public boolean achievementCompleted(
            final IHabbo habbo,
            final IAchievement achievement
    ) {
        final IHabboAchievementProgress progress = habbo.getAchievements().getProgressByAchievementName(achievement.getName());

        if(progress == null || progress.getCurrentProgress() < 0) return false;

        final IAchievementLevel level = this.getCurrentLevel(achievement, progress.getCurrentProgress());

        if(level == null) return false;

        final IAchievementLevel nextLevel = achievement.getLevel(level.getLevel() + 1);

        return nextLevel == null && progress.getCurrentProgress() >= level.getProgressNeeded();
    }

    @Override
    public IAchievementLevel getCurrentLevel(IAchievement achievement, int progress) {
        if(progress <= 0) return null;

        return achievement.getLevels().values().stream()
                .filter(achievementProgress -> achievementProgress.getLevel() >= progress)
                .findFirst()
                .orElse(null);
    }

    @Override
    public IAchievementLevel getNextLevel(IAchievement achievement, int progress) {
        return achievement.getLevels().values().stream()
                .filter(achievementProgress -> achievementProgress.getLevel() == (progress + 1))
                .findFirst()
                .orElse(null);
    }

    @Override
    public IHabboAchievementProgress getEmptyAchievementProgress() {
        return this.emptyAchievementProgress;
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
