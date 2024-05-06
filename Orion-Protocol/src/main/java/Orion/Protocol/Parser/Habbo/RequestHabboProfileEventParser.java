package Orion.Protocol.Parser.Habbo;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestHabboProfileEventParser implements IEventParser {
    public int habboId;

    @Override
    public int getId() {
        return EventHeaders.RequestHabboProfileEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.habboId = messageEvent.readInt();
    }
}
