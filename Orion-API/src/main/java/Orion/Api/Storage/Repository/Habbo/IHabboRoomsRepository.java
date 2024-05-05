package Orion.Api.Storage.Repository.Habbo;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IHabboRoomsRepository {
    void loadRoomsForHabbo(IConnectionResultConsumer consumer, int habboId);

    void loadFavoriteRoomsForHabbo(IConnectionResultConsumer consumer, int habboId);

    void loadRoomHistoryForHabbo(IConnectionResultConsumer consumer, int habboId, long timestamp);

    void loadRoomsWithRightsForHabbo(IConnectionResultConsumer consumer, int habboId);
}
