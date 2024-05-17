package Orion.Protocol.Parser.Habbo.Entity;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class LookAtEntityEventParser implements IEventParser {
    public int positionX;
    public int positionY;

    @Override
    public int getId() {
        return EventHeaders.LookAtEntityEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.positionX = messageEvent.readInt();
        this.positionY = messageEvent.readInt();
    }
}
