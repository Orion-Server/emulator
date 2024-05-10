package Orion.Protocol.Message.Event.Room;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Protocol.Message.Composer.Room.RoomHeightmapComposer;
import Orion.Protocol.Message.Composer.Room.RoomModelComposer;
import Orion.Protocol.Message.Composer.Room.RoomOpenComposer;
import Orion.Protocol.Message.Composer.Room.RoomRelativeMapComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class JoinRoomEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.JoinRoomEvent;
    }

    @Override
    public IEventParser getParser() {
        return null;
    }

    @Override
    public void handle(ISession session) {
        if(session.getHabbo().getEntity() == null || session.getHabbo().getEntity().getRoom() == null) return;

        final IRoom room = session.getHabbo().getEntity().getRoom();

        room.initialize();

        session.send(
                new RoomOpenComposer(),
                new RoomModelComposer(room),
                new RoomRelativeMapComposer(room),
                new RoomHeightmapComposer(room)
        );
    }
}