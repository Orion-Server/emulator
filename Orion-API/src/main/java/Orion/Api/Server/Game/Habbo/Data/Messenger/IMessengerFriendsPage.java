package Orion.Api.Server.Game.Habbo.Data.Messenger;

import java.util.concurrent.ConcurrentHashMap;

public interface IMessengerFriendsPage {
    ConcurrentHashMap<Integer, IMessengerFriend> getFriends();

    void addFriend(IMessengerFriend friend);

    void removeFriend(IMessengerFriend friend);

    boolean hasFriend(IMessengerFriend friend);

    IMessengerFriend getFriend(int id);
}
