package Orion.Storage.Repository.Room;

import Orion.Api.Storage.Repository.Room.IRoomItemsRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.Room.RoomItemsQuery;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Singleton;

@Singleton
public class RoomItemsRepository extends DatabaseRepository implements IRoomItemsRepository {
    public void loadAllItemDefinitions(IConnectionResultConsumer consumer) {
        this.select(RoomItemsQuery.LOAD_ALL_ITEM_DEFINITIONS.get(), consumer);
    }

    public void loadItemsByRoomId(IConnectionResultConsumer consumer, int roomId) {
        this.select(RoomItemsQuery.LOAD_ITEMS_BY_ROOM_ID.get(), consumer, roomId);
    }
}
