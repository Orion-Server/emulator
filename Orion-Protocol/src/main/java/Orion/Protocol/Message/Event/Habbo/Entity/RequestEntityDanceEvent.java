package Orion.Protocol.Message.Event.Habbo.Entity;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Protocol.Message.Composer.Room.Entities.EntityDanceComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestEntityDanceEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.RequestEntityDanceEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        if(!session.getHabbo().isInRoom()) return;

        final int danceId = event.readInt();

        session.getHabbo().getEntity().setDanceId(
                session.getHabbo().getEntity().getDanceId() == danceId ? 0 : danceId
        );

        session.getHabbo().getEntity().getRoom().broadcastMessage(new EntityDanceComposer(
                session.getHabbo().getEntity().getVirtualId(),
                session.getHabbo().getEntity().getDanceId()
        ));
    }
}
