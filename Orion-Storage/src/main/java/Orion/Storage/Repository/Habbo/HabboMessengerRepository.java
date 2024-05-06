package Orion.Storage.Repository.Habbo;

import Orion.Api.Storage.Repository.Habbo.IHabboMessengerRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.Habbo.HabboMessengerQuery;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Singleton;

@Singleton
public class HabboMessengerRepository extends DatabaseRepository implements IHabboMessengerRepository {
    @Override
    public void loadAllMessengerCategories(IConnectionResultConsumer consumer, int habboId) {
        this.select(HabboMessengerQuery.LOAD_ALL_MESSENGER_CATEGORIES.get(), consumer, habboId);
    }

    @Override
    public void loadAllMessengerFriends(IConnectionResultConsumer consumer, int habboId) {
        this.select(HabboMessengerQuery.LOAD_ALL_MESSENGER_FRIENDS.get(), consumer, habboId);
    }

    @Override
    public void loadAllMessengerFriendRequests(IConnectionResultConsumer consumer, int habboId) {
        this.select(HabboMessengerQuery.LOAD_ALL_MESSENGER_FRIEND_REQUESTS.get(), consumer, habboId);
    }
}
