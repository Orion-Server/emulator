package Orion.Protocol.Message.Event.Room;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.IRoomManager;
import Orion.Protocol.Message.Composer.HotelView.GoToHotelViewComposer;
import Orion.Protocol.Message.Composer.Room.RoomDataComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestRoomDataEvent implements IMessageEventHandler {
    @Inject
    private IRoomManager roomManager;

    @Override
    public int getId() {
        return EventHeaders.RequestRoomDataEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        final int roomId = event.readInt();
        final int enterRoom = event.readInt();
        final int forwardRoom = event.readInt();

        final IRoom room = this.roomManager.getRoomById(roomId);

        if (room == null) {
            session.send(new GoToHotelViewComposer());
            return;
        }

        final boolean shouldEnterRoom = enterRoom == 1 && forwardRoom == 0;

        session.send(new RoomDataComposer(session.getHabbo(), room, true, shouldEnterRoom));
    }
}
