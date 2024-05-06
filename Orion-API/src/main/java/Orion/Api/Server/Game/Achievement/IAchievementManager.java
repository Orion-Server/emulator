package Orion.Api.Server.Game.Achievement;

import Orion.Api.Server.Game.Achievement.Data.IAchievement;
import Orion.Api.Server.Game.Achievement.Data.IAchievementLevel;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Util.Initializable;
import gnu.trove.map.hash.THashMap;

public interface IAchievementManager extends Initializable {
    IAchievement getAchievement(String name);

    THashMap<String, IAchievement> getAchievements();

    IAchievementLevel getCurrentLevel(IAchievement achievement, int progress);

    IAchievementLevel getNextLevel(IAchievement achievement, int currentProgress);

    boolean achievementCompleted(final IHabbo habbo, final IAchievement achievement);
}
