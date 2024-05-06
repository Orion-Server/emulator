package Orion.Game.Habbo.Factory;

import Orion.Api.Server.Game.Achievement.Data.IAchievement;
import Orion.Api.Server.Game.Achievement.Data.IAchievementLevel;
import Orion.Api.Server.Game.Achievement.IAchievementManager;
import Orion.Api.Server.Game.Habbo.Data.Achievement.IHabboAchievementProgress;
import Orion.Api.Server.Game.Habbo.Data.IHabboAchievements;
import Orion.Api.Storage.Repository.Habbo.IHabboAchievementsRepository;
import Orion.Game.Habbo.Data.Achievement.HabboAchievementProgress;
import Orion.Game.Habbo.Data.HabboAchievements;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import gnu.trove.map.hash.THashMap;

@Singleton
public class HabboAchievementsFactory {
    @Inject
    private IHabboAchievementsRepository repository;

    @Inject
    private IAchievementManager achievementManager;

    public IHabboAchievements create(int habboId) {
        final THashMap<String, IHabboAchievementProgress> achievementProgress = new THashMap<>();

        this.repository.loadAllAchievements(result -> {
            if(result == null) return;

            final String achievementName = result.getString("achievement_name");
            final int currentProgress = result.getInt("progress");

            final IAchievement achievement = this.achievementManager.getAchievement(achievementName);

            final IAchievementLevel currentLevel = this.achievementManager.getCurrentLevel(achievement, currentProgress);
            final IAchievementLevel nextLevel = this.achievementManager.getNextLevel(achievement, currentLevel == null ? 0 : currentLevel.getLevel());

            achievementProgress.putIfAbsent(achievementName, new HabboAchievementProgress(currentProgress, currentLevel, nextLevel));
        }, habboId);

        return new HabboAchievements(achievementProgress);
    }
}
