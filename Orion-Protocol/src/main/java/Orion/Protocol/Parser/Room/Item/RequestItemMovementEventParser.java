package Orion.Protocol.Parser.Room.Item;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestItemMovementEventParser implements IEventParser {
    public int itemId;
    public int newX;
    public int newY;
    public int newRotation;

    @Override
    public int getId() {
        return EventHeaders.RequestItemMovementEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.itemId = messageEvent.readInt();
        this.newX = messageEvent.readInt();
        this.newY = messageEvent.readInt();
        this.newRotation = messageEvent.readInt();
    }
}
