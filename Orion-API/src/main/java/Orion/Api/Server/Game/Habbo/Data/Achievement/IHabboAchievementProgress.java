package Orion.Api.Server.Game.Habbo.Data.Achievement;

import Orion.Api.Server.Game.Achievement.Data.IAchievementLevel;

public interface IHabboAchievementProgress {
    int getCurrentProgress();

    IAchievementLevel getCurrentAchievementLevel();

    int getCurrentLevel();

    IAchievementLevel getNextAchievementLevel();

    int getNextLevel();

    int getCurrentLevelProgress();

    int getNextLevelProgress();

    int getNextLevelRewardAmount();

    int getNextLevelRewardType();
}
