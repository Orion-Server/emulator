package Orion.Protocol.Message.Event.Habbo.Entity;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class LookAtEntityEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.LookAtEntityEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        if(!session.getHabbo().isInRoom() || session.getHabbo().getEntity().isWalking()) return;

        final int positionX = event.readInt();
        final int positionY = event.readInt();

        // TODO: Implement entity is visible

        session.getHabbo().getEntity().lookAt(positionX, positionY);
    }
}
