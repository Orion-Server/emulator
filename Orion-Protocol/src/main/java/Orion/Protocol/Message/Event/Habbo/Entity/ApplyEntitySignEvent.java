package Orion.Protocol.Message.Event.Habbo.Entity;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Room.Object.Entity.Enum.RoomEntityStatus;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Habbo.Entity.ApplyEntitySignEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ApplyEntitySignEvent implements IMessageEventHandler {
    @Inject
    private ApplyEntitySignEventParser parser;

    @Override
    public int getId() {
        return EventHeaders.ApplyEntitySignEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    @Override
    public void handle(ISession session) {
        if(!session.getHabbo().isInRoom()) return;

        session.getHabbo().getEntity().setStatus(RoomEntityStatus.SIGN, String.valueOf(this.parser.signType.get()));
        session.getHabbo().getEntity().setNeedsUpdate(true);
    }
}
