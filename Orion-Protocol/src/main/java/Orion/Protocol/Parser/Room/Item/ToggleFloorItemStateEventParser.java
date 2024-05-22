package Orion.Protocol.Parser.Room.Item;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class ToggleFloorItemStateEventParser implements IEventParser {
    public int itemId;
    public int state;

    @Override
    public int getId() {
        return EventHeaders.ToggleFloorItemStateEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.itemId = messageEvent.readInt();
        this.state = messageEvent.readInt();
    }
}
