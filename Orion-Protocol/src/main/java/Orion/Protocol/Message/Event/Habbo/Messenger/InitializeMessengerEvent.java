package Orion.Protocol.Message.Event.Habbo.Messenger;

import Orion.Api.Networking.Message.IComposer;
import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendsPage;
import Orion.Protocol.Message.Composer.Habbo.Messenger.InitializeMessengerComposer;
import Orion.Protocol.Message.Composer.Habbo.Messenger.MessengerFriendsComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.Set;

@Singleton
public class InitializeMessengerEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.InitializeMessengerEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        final ArrayList<IComposer> messages = new ArrayList<>();

        messages.add(new InitializeMessengerComposer(session.getHabbo()));

        final Set<IMessengerFriendsPage> friendsPages = session.getHabbo().getMessenger().getFriends();
        int pageIndex = 0;

        for (final IMessengerFriendsPage page : friendsPages) {
            messages.add(new MessengerFriendsComposer(friendsPages.size(), pageIndex, page));
            pageIndex++;
        }

        session.send(messages);
    }
}
