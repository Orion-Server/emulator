package Orion.Api.Storage.Repository.Habbo;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IHabboAchievementsRepository {
    void loadAllAchievements(IConnectionResultConsumer consumer, int habboId);
}
