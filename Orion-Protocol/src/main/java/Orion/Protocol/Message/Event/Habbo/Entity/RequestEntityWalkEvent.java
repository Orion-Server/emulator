package Orion.Protocol.Message.Event.Habbo.Entity;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Habbo.Entity.RequestEntityWalkEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestEntityWalkEvent implements IMessageEventHandler {
    @Inject
    private RequestEntityWalkEventParser parser;

    @Override
    public int getId() {
        return EventHeaders.RequestEntityWalkEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    @Override
    public void handle(ISession session) {
        final IHabboEntity entity = session.getHabbo().getEntity();

        if(entity == null || entity.getPosition().equals(this.parser.goalX, this.parser.goalY)) return;

        // TODO: Check teleport, canWalk, isVisible, warp, etc

        entity.getWalkComponent().walkToPosition(this.parser.goalX, this.parser.goalY);
    }
}
