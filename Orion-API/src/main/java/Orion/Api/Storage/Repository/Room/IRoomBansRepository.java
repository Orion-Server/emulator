package Orion.Api.Storage.Repository.Room;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IRoomBansRepository {
    void loadAllValidBans(IConnectionResultConsumer consumer, int roomId, long timestamp);
}
