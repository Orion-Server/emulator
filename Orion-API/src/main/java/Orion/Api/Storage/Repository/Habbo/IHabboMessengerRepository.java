package Orion.Api.Storage.Repository.Habbo;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IHabboMessengerRepository {
    void loadAllMessengerCategories(IConnectionResultConsumer consumer, int habboId);

    void loadAllMessengerFriends(IConnectionResultConsumer consumer, int habboId);

    void loadAllMessengerFriendRequests(IConnectionResultConsumer consumer, int habboId);
}
