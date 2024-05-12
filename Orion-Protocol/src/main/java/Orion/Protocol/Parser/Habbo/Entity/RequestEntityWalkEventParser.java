package Orion.Protocol.Parser.Habbo.Entity;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestEntityWalkEventParser implements IEventParser {
    public int goalX;
    public int goalY;

    @Override
    public int getId() {
        return EventHeaders.RequestEntityWalkEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.goalX = messageEvent.readInt();
        this.goalY = messageEvent.readInt();
    }
}
