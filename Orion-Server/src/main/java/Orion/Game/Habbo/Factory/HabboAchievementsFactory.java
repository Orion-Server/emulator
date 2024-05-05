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

        this.repository.loadAllAchievementsByHabboId(result -> {
            if(result == null) return;

            final String achievementName = result.getString("achievement_name");
            final int progressValue = result.getInt("progress");

            final IAchievement achievement = this.achievementManager.getAchievement(achievementName);

            final IAchievementLevel currentLevel = this.achievementManager.getCurrentLevel(achievement, progressValue);
            final IAchievementLevel nextLevel = this.achievementManager.getNextLevel(achievement, progressValue);

            achievementProgress.putIfAbsent(achievementName, new HabboAchievementProgress(progressValue, currentLevel, nextLevel));
        }, habboId);

        return new HabboAchievements(achievementProgress);
    }
}
