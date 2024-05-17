package Orion.Protocol.Message.Event.Habbo.Entity;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestEntitySitEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.RequestEntitySitEvent;
    }

    @Override
    public IEventParser getParser() {
        return null;
    }

    @Override
    public void handle(ISession session) {
        if(!session.getHabbo().isInRoom() || session.getHabbo().getEntity().isWalking()) return;

        // TODO: Implement entity is visible

        session.getHabbo().getEntity().sit(0.5);
    }
}
