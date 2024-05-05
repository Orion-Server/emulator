package Orion.Protocol.Message.Event.Lifecycle;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class MemoryPerformanceEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.MemoryPerformanceEvent;
    }

    @Override
    public IEventParser getParser() {
        return null;
    }

    @Override
    public void handle(ISession session) {}
}
