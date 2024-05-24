package Orion.Protocol.Message.Event.Lifecycle;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class PongEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.PongEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {

    }
}
