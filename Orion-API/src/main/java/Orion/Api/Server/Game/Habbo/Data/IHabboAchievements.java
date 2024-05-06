package Orion.Api.Server.Game.Habbo.Data;

import Orion.Api.Server.Game.Achievement.Data.IAchievement;
import Orion.Api.Server.Game.Habbo.Data.Achievement.IHabboAchievementProgress;
import gnu.trove.map.hash.THashMap;

public interface IHabboAchievements {
    THashMap<String, IHabboAchievementProgress> getAchievementProgressions();

    IHabboAchievementProgress getProgressByAchievement(final IAchievement achievement);
}
