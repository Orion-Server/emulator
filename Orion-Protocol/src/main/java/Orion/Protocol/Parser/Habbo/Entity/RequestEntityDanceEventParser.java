package Orion.Protocol.Parser.Habbo.Entity;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestEntityDanceEventParser implements IEventParser {
    public int danceId;

    @Override
    public int getId() {
        return EventHeaders.RequestEntityDanceEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.danceId = messageEvent.readInt();
    }
}
