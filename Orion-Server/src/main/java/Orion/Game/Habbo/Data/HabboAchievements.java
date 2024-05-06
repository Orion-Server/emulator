package Orion.Game.Habbo.Data;

import Orion.Api.Server.Game.Achievement.Data.IAchievement;
import Orion.Api.Server.Game.Habbo.Data.Achievement.IHabboAchievementProgress;
import Orion.Api.Server.Game.Habbo.Data.IHabboAchievements;
import gnu.trove.map.hash.THashMap;

public class HabboAchievements implements IHabboAchievements {
    private final THashMap<String, IHabboAchievementProgress> achievementProgress;

    public HabboAchievements(
            final THashMap<String, IHabboAchievementProgress> achievementProgress
    ) {
        this.achievementProgress = achievementProgress;
    }

    @Override
    public THashMap<String, IHabboAchievementProgress> getAchievementProgressions() {
        return this.achievementProgress;
    }

    @Override
    public IHabboAchievementProgress getProgressByAchievement(final IAchievement achievement) {
        if(!this.achievementProgress.containsKey(achievement.getName())) {
            return achievement.getFirstProgress();
        }

        return this.achievementProgress.get(achievement.getName());
    }
}
