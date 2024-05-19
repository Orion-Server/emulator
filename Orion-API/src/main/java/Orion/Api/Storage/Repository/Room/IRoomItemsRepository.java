package Orion.Api.Storage.Repository.Room;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IRoomItemsRepository {
    void loadItemsByRoomId(IConnectionResultConsumer consumer, int roomId);

    void loadAllItemDefinitions(IConnectionResultConsumer consumer);
}
