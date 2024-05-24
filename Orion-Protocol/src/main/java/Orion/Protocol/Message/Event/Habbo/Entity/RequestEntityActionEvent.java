package Orion.Protocol.Message.Event.Habbo.Entity;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.Room.Object.Entity.Enum.EntityActionType;
import Orion.Protocol.Message.Composer.Room.Entities.EntityActionComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestEntityActionEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.RequestEntityActionEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        if(!session.getHabbo().isInRoom()) return;

        final EntityActionType actionType = EntityActionType.fromValue(event.readInt());

        if(actionType == null) return;

        // TODO: Implement entity is visible

        session.getHabbo().getEntity().getRoom().broadcastMessage(new EntityActionComposer(
                session.getHabbo().getEntity().getVirtualId(), actionType
        ));
    }
}
