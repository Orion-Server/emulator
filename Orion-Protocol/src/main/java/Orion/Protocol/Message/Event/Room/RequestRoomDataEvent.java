package Orion.Protocol.Message.Event.Room;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.IRoomManager;
import Orion.Protocol.Message.Composer.HotelView.GoToHotelViewComposer;
import Orion.Protocol.Message.Composer.Room.RoomDataComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Room.RequestRoomDataParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestRoomDataEvent implements IMessageEventHandler {
    @Inject
    private RequestRoomDataParser parser;

    @Inject
    private IRoomManager roomManager;

    @Override
    public int getId() {
        return EventHeaders.RequestRoomDataEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    @Override
    public void handle(ISession session) {
        final IRoom room = this.roomManager.getRoomById(this.parser.roomId);

        if (room == null) {
            session.send(new GoToHotelViewComposer());
            return;
        }

        final boolean enterRoom = this.parser.enterRoom == 1 && this.parser.forwardRoom == 0;

        session.send(new RoomDataComposer(session.getHabbo(), room, true, enterRoom));
    }
}
