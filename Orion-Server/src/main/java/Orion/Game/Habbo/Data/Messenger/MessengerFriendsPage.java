package Orion.Game.Habbo.Data.Messenger;

import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriend;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendsPage;

import java.util.concurrent.ConcurrentHashMap;

public class MessengerFriendsPage implements IMessengerFriendsPage {
    private final ConcurrentHashMap<Integer, IMessengerFriend> friends;

    public MessengerFriendsPage() {
        this.friends = new ConcurrentHashMap<>();
    }

    @Override
    public ConcurrentHashMap<Integer, IMessengerFriend> getFriends() {
        return this.friends;
    }

    @Override
    public void addFriend(IMessengerFriend friend) {
        this.friends.put(friend.getId(), friend);
    }

    @Override
    public void removeFriend(IMessengerFriend friend) {
        this.friends.remove(friend.getId());
    }

    @Override
    public boolean hasFriend(IMessengerFriend friend) {
        return this.friends.containsKey(friend.getId());
    }

    @Override
    public IMessengerFriend getFriend(int id) {
        return this.friends.get(id);
    }
}
