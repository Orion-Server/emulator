package Orion.Game.Achievement.Data;

import Orion.Api.Server.Game.Achievement.Data.IAchievementLevel;
import Orion.Api.Storage.Result.IConnectionResult;

public class AchievementLevel implements IAchievementLevel {
    private int level;
    private int rewardAmount;
    private int rewardType;
    private int points;
    private int progressNeeded;

    public AchievementLevel(final IConnectionResult data) {
        try {
            this.fill(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getRewardAmount() {
        return this.rewardAmount;
    }

    @Override
    public int getRewardType() {
        return this.rewardType;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public int getProgressNeeded() {
        return this.progressNeeded;
    }

    @Override
    public void fill(IConnectionResult data) throws Exception {
        this.level = data.getInt("level");
        this.rewardAmount = data.getInt("reward_amount");
        this.rewardType = data.getInt("reward_type");
        this.points = data.getInt("points");
        this.progressNeeded = data.getInt("progress_needed");
    }
}
