package Orion.Game.Habbo.Data;

import Orion.Api.Server.Game.Habbo.Data.IHabboMessenger;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerCategory;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendRequest;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendsPage;
import gnu.trove.set.hash.THashSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class HabboMessenger implements IHabboMessenger {
    private List<IMessengerCategory> categories;

    private Set<IMessengerFriendsPage> friends;

    private THashSet<IMessengerFriendRequest> friendRequests;

    public HabboMessenger() {
        this.categories = new ArrayList<>();

        this.friendRequests = new THashSet<>();
        this.friends = ConcurrentHashMap.newKeySet();
    }

    @Override
    public void setCategories(final List<IMessengerCategory> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
    }

    public List<IMessengerCategory> getCategories() {
        return this.categories;
    }

    @Override
    public void setFriends(final Set<IMessengerFriendsPage> friends) {
        this.friends.clear();
        this.friends.addAll(friends);
    }

    @Override
    public Set<IMessengerFriendsPage> getFriends() {
        return this.friends;
    }

    @Override
    public void setFriendRequests(final THashSet<IMessengerFriendRequest> friendRequests) {
        this.friendRequests.clear();
        this.friendRequests.addAll(friendRequests);
    }

    @Override
    public THashSet<IMessengerFriendRequest> getFriendRequests() {
        return this.friendRequests;
    }

    @Override
    public boolean hasFriend(final int id) {
        for (IMessengerFriendsPage friend : this.friends) {
            if (friend.getFriend(id) != null) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void dispose() {
        this.categories.clear();
        this.categories = null;

        this.friends.clear();
        this.friends = null;

        this.friendRequests.clear();
        this.friendRequests = null;
    }
}
