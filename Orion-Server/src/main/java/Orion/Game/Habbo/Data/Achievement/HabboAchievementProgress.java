package Orion.Game.Habbo.Data.Achievement;

import Orion.Api.Server.Game.Achievement.Data.IAchievementLevel;
import Orion.Api.Server.Game.Habbo.Data.Achievement.IHabboAchievementProgress;

public class HabboAchievementProgress implements IHabboAchievementProgress {
    private final int currentProgress;
    private final IAchievementLevel currentLevel;
    private final IAchievementLevel nextLevel;

    public HabboAchievementProgress(
            final int currentProgress,
            final IAchievementLevel currentLevel,
            final IAchievementLevel nextLevel
    ) {
        this.currentProgress = currentProgress;
        this.currentLevel = currentLevel;
        this.nextLevel = nextLevel;
    }

    @Override
    public int getCurrentProgress() {
        return Math.max(this.currentProgress, 0);

    }

    @Override
    public IAchievementLevel getCurrentAchievementLevel() {
        return this.currentLevel;
    }

    @Override
    public int getCurrentLevel() {
        if(this.nextLevel != null) {
            return this.nextLevel.getLevel();
        }

        if(this.currentLevel != null) {
            return this.currentLevel.getLevel();
        }

        return 0;
    }

    @Override
    public IAchievementLevel getNextAchievementLevel() {
        return this.nextLevel;
    }

    @Override
    public int getNextLevel() {
        if(this.nextLevel == null) return 0;

        return this.nextLevel.getLevel();
    }

    @Override
    public int getCurrentLevelProgress() {
        if(this.currentLevel == null) return 0;

        return this.currentLevel.getProgressNeeded();
    }

    @Override
    public int getNextLevelProgress() {
        if(this.nextLevel == null) return -1;

        return this.nextLevel.getProgressNeeded();
    }

    @Override
    public int getNextLevelRewardAmount() {
        if(this.nextLevel == null) return -1;

        return this.nextLevel.getRewardAmount();
    }

    public int getNextLevelRewardType() {
        if(this.nextLevel == null) return -1;

        return this.nextLevel.getRewardType();
    }
}
