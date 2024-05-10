package Orion.Storage.Repository.Room;

import Orion.Api.Storage.Repository.Room.IRoomBansRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.Room.RoomBanQuery;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Singleton;

@Singleton
public class RoomBansRepository extends DatabaseRepository implements IRoomBansRepository {
    @Override
    public void loadAllValidBans(IConnectionResultConsumer consumer, int roomId, long timestamp) {
        this.select(RoomBanQuery.SELECT_ALL_ROOM_BANS.get(), consumer, roomId, timestamp);
    }
}
