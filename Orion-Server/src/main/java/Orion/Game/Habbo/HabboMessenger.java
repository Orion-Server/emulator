package Orion.Game.Habbo;

import Orion.Api.Server.Game.Habbo.Data.IHabboMessenger;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerCategory;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendRequest;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendsPage;
import gnu.trove.set.hash.THashSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class HabboMessenger implements IHabboMessenger {
    private List<IMessengerCategory> categories;

    private ConcurrentLinkedQueue<IMessengerFriendsPage> friends;

    private THashSet<IMessengerFriendRequest> friendRequests;

    public HabboMessenger() {
        this.categories = new ArrayList<>();

        this.friendRequests = new THashSet<>();
        this.friends = new ConcurrentLinkedQueue<>();
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
    public void setFriends(final ConcurrentLinkedQueue<IMessengerFriendsPage> friends) {
        this.friends.clear();
        this.friends.addAll(friends);
    }

    @Override
    public ConcurrentLinkedQueue<IMessengerFriendsPage> getFriends() {
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
    public void dispose() {
        this.categories.clear();
        this.categories = null;

        this.friends.clear();
        this.friends = null;

        this.friendRequests.clear();
        this.friendRequests = null;
    }
}
