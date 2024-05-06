package Orion.Api.Server.Game.Habbo.Data;

import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerCategory;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriend;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendRequest;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendsPage;
import Orion.Api.Util.IDisposable;
import gnu.trove.set.hash.THashSet;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface IHabboMessenger extends IDisposable {
    List<IMessengerCategory> getCategories();

    void setCategories(final List<IMessengerCategory> categories);

    void setFriendRequests(final THashSet<IMessengerFriendRequest> friendRequests);

    THashSet<IMessengerFriendRequest> getFriendRequests();

    void setFriends(final ConcurrentLinkedQueue<IMessengerFriendsPage> friends);

    ConcurrentLinkedQueue<IMessengerFriendsPage> getFriends();
}
