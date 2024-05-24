package Orion.Protocol.Message.Event.Habbo.Messenger;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Protocol.Message.Composer.Habbo.Messenger.MessengerFriendRequestsComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestMessengerFriendRequestsEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.RequestMessengerFriendRequestsEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        session.send(new MessengerFriendRequestsComposer(session.getHabbo()));
    }
}
