package Orion.Storage.Repository.Habbo;

import Orion.Api.Storage.Repository.Habbo.IHabboRoomsRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.Habbo.HabboRoomQuery;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Singleton;

@Singleton
public class HabboRoomsRepository extends DatabaseRepository implements IHabboRoomsRepository {
    @Override
    public void loadRoomsForHabbo(IConnectionResultConsumer consumer, int habboId) {
        this.select(HabboRoomQuery.SELECT_ALL_ROOMS.get(), consumer, habboId);
    }

    @Override
    public void loadFavoriteRoomsForHabbo(IConnectionResultConsumer consumer, int habboId) {
        this.select(HabboRoomQuery.SELECT_ALL_FAVORITE_ROOMS.get(), consumer, habboId);
    }

    @Override
    public void loadRoomHistoryForHabbo(IConnectionResultConsumer consumer, int habboId, long timestamp) {
        this.select(HabboRoomQuery.SELECT_ALL_ROOM_HISTORY.get(), consumer, habboId, timestamp, habboId);
    }

    @Override
    public void loadRoomsWithRightsForHabbo(IConnectionResultConsumer consumer, int habboId) {
        this.select(HabboRoomQuery.SELECT_ALL_ROOMS_WITH_RIGHTS.get(), consumer, habboId);
    }
}
