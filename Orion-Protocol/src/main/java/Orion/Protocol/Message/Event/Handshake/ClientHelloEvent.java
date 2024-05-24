package Orion.Protocol.Message.Event.Handshake;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Protocol.Annotations.HandshakeEvent;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
@HandshakeEvent
public class ClientHelloEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.ClientHelloEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        session.setClientVersion(event.readString());
    }
}
