package Orion.Api.Server.Game.Achievement.Data;

import Orion.Api.Server.Game.Achievement.Enum.AchievementCategory;
import Orion.Api.Server.Game.Habbo.Data.Achievement.IHabboAchievementProgress;
import Orion.Api.Util.IFillable;
import gnu.trove.map.hash.THashMap;

public interface IAchievement extends IFillable {
    int getId();

    String getName();

    AchievementCategory getCategory();

    IAchievementLevel getLevel(int level);

    THashMap<Integer, IAchievementLevel> getLevels();

    int getLevelsCount();

    void addLevel(IAchievementLevel level);

    String getCompleteName(final IAchievementLevel currentProgress, final IAchievementLevel nextProgress);

    IHabboAchievementProgress getFirstProgress();
}
