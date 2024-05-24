package Orion.Protocol.Message.Event.Lifecycle;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Protocol.Message.Composer.LifeCycle.PongComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class PingEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.PingEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        session.send(new PongComposer(event.readInt()));
    }
}
