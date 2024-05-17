package Orion.Protocol.Message.Event.Habbo.Entity;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Habbo.Entity.LookAtEntityEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LookAtEntityEvent implements IMessageEventHandler {
    @Inject
    private LookAtEntityEventParser parser;

    @Override
    public int getId() {
        return EventHeaders.LookAtEntityEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    @Override
    public void handle(ISession session) {
        if(!session.getHabbo().isInRoom() || session.getHabbo().getEntity().isWalking()) return;

        // TODO: Implement entity is visible

        session.getHabbo().getEntity().lookAt(this.parser.positionX, this.parser.positionY);
    }
}
