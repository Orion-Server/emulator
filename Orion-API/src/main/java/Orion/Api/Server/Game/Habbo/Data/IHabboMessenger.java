package Orion.Api.Server.Game.Habbo.Data;

import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerCategory;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendRequest;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendsPage;
import Orion.Api.Util.IDisposable;
import gnu.trove.set.hash.THashSet;

import java.util.List;
import java.util.Set;

public interface IHabboMessenger extends IDisposable {
    List<IMessengerCategory> getCategories();

    void setCategories(final List<IMessengerCategory> categories);

    void setFriendRequests(final THashSet<IMessengerFriendRequest> friendRequests);

    THashSet<IMessengerFriendRequest> getFriendRequests();

    boolean hasFriend(final int id);

    void setFriends(final Set<IMessengerFriendsPage> friends);

    Set<IMessengerFriendsPage> getFriends();
}
