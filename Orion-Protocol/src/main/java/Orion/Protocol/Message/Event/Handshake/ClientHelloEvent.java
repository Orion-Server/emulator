package Orion.Protocol.Message.Event.Handshake;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Hanshake.ClientHelloEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ClientHelloEvent implements IMessageEventHandler {
    @Inject
    private ClientHelloEventParser parser;

    @Override
    public int getId() {
        return EventHeaders.ClientHelloEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    public void handle(ISession session) {
        session.setClientVersion(this.parser.clientVersion);
    }
}
