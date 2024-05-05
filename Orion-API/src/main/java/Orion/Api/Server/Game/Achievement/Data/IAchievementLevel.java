package Orion.Api.Server.Game.Achievement.Data;

import Orion.Api.Util.IFillable;

public interface IAchievementLevel extends IFillable {
    int getLevel();

    int getRewardAmount();

    int getRewardType();

    int getPoints();

    int getProgressNeeded();
}
