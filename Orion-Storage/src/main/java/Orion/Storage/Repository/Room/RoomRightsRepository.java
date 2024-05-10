package Orion.Storage.Repository.Room;

import Orion.Api.Storage.Repository.Room.IRoomRightsRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.Room.RoomRightsQuery;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Singleton;

@Singleton
public class RoomRightsRepository extends DatabaseRepository implements IRoomRightsRepository {
    public void loadHabboWithRights(IConnectionResultConsumer consumer, int roomId) {
        this.select(RoomRightsQuery.LOAD_HABBO_WITH_RIGHTS.get(), consumer, roomId);
    }
}
