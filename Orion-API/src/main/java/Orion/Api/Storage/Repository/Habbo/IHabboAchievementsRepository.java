package Orion.Api.Storage.Repository.Habbo;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IHabboAchievementsRepository {
    void loadAllAchievementsByHabboId(IConnectionResultConsumer consumer, int habboId);
}
