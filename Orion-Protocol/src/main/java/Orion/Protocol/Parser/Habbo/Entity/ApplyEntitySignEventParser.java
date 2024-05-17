package Orion.Protocol.Parser.Habbo.Entity;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Room.Object.Entity.Enum.EntitySignType;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class ApplyEntitySignEventParser implements IEventParser {
    public EntitySignType signType;

    @Override
    public int getId() {
        return EventHeaders.ApplyEntitySignEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.signType = EntitySignType.fromValue(messageEvent.readInt());
    }
}
