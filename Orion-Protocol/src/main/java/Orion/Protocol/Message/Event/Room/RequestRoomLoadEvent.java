package Orion.Protocol.Message.Event.Room;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.Room.Handler.IJoinRoomHandler;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.IRoomManager;
import Orion.Protocol.Message.Composer.HotelView.GoToHotelViewComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestRoomLoadEvent implements IMessageEventHandler {
    @Inject
    private IRoomManager roomManager;

    @Inject
    private IJoinRoomHandler joinRoomHandler;

    @Override
    public int getId() {
        return EventHeaders.RequestRoomLoadEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        final int roomId = event.readInt();
        final String password = event.readString();

        final IRoom room = this.roomManager.getRoomById(roomId);

        if(room == null) {
            session.send(new GoToHotelViewComposer());
            return;
        }

        joinRoomHandler.prepareRoom(room, session.getHabbo(), password);
    }
}
