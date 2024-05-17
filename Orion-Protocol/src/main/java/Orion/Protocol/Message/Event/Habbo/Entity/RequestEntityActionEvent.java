package Orion.Protocol.Message.Event.Habbo.Entity;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Composer.Room.Entities.EntityActionComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Habbo.Entity.RequestEntityActionEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestEntityActionEvent implements IMessageEventHandler {
    @Inject
    private RequestEntityActionEventParser parser;

    @Override
    public int getId() {
        return EventHeaders.RequestEntityActionEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    @Override
    public void handle(ISession session) {
        if(!session.getHabbo().isInRoom()) return;

        // TODO: Implement entity is visible

        session.getHabbo().getEntity().getRoom().broadcastMessage(new EntityActionComposer(
                session.getHabbo().getEntity().getVirtualId(),
                this.parser.actionType
        ));
    }
}
