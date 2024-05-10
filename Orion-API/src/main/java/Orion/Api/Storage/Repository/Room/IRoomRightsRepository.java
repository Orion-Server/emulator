package Orion.Api.Storage.Repository.Room;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IRoomRightsRepository {
    void loadHabboWithRights(IConnectionResultConsumer consumer, int roomId);
}
