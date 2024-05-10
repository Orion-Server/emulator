package Orion.Protocol.Message.Event.Room;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Room.Handler.IJoinRoomHandler;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestRoomHeightmapEvent implements IMessageEventHandler {
    @Inject
    private IJoinRoomHandler joinRoomHandler;

    @Override
    public int getId() {
        return EventHeaders.RequestRoomHeightmapEvent;
    }

    @Override
    public IEventParser getParser() {
        return null;
    }

    @Override
    public void handle(ISession session) {
        joinRoomHandler.finalizeRoomEnter(
                session.getHabbo().getEntity().getRoom(),
                session.getHabbo()
        );
    }
}
