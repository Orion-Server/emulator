package Orion.Protocol.Message.Event.Habbo.Entity;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Composer.Room.Entities.EntityDanceComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Habbo.Entity.RequestEntityDanceEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestEntityDanceEvent implements IMessageEventHandler {
    @Inject
    private RequestEntityDanceEventParser parser;

    @Override
    public int getId() {
        return EventHeaders.RequestEntityDanceEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    @Override
    public void handle(ISession session) {
        if(!session.getHabbo().isInRoom()) return;

        final int danceId = session.getHabbo().getEntity().getDanceId() == this.parser.danceId
                ? 0
                : this.parser.danceId;

        session.getHabbo().getEntity().setDanceId(danceId);

        session.getHabbo().getEntity().getRoom().broadcastMessage(new EntityDanceComposer(
                session.getHabbo().getEntity().getVirtualId(),
                session.getHabbo().getEntity().getDanceId()
        ));
    }
}
