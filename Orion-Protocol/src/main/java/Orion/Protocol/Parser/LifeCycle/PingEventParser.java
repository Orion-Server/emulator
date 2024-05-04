package Orion.Protocol.Parser.LifeCycle;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class PingEventParser implements IEventParser {
    public int ping;

    @Override
    public int getId() {
        return EventHeaders.PingEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.ping = messageEvent.readInt();
    }
}
