package Orion.Protocol.Message.Event.Habbo.Entity;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestEntityWalkEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.RequestEntityWalkEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        if(!session.getHabbo().isInRoom()) return;

        final int goalX = event.readInt();
        final int goalY = event.readInt();

        final IHabboEntity entity = session.getHabbo().getEntity();

        if(entity.getPosition().equals(goalX, goalY)) return;

        // TODO: Check teleport, canWalk, isVisible, warp, etc

        entity.getWalkComponent().walkToPosition(goalX, goalY);
    }
}
