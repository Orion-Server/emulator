package Orion.Api.Storage.Repository.Achievement;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IAchievementRepository {
    void loadAllAchievements(IConnectionResultConsumer consumer);
}
