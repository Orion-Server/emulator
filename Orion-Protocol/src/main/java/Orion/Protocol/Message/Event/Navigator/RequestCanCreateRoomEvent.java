package Orion.Protocol.Message.Event.Navigator;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.Room.Utils.RoomEnvironmentVariables;
import Orion.Protocol.Message.Composer.Navigator.CanCreateRoomComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestCanCreateRoomEvent implements IMessageEventHandler {
    @Inject
    private RoomEnvironmentVariables roomEnvironmentVariables;

    @Override
    public int getId() {
        return EventHeaders.RequestCanCreateRoomEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        final int canCreateRoom = session.getHabbo().getRooms().getOwnRooms().size() < this.roomEnvironmentVariables.userRoomsLimit ? 0 : 1;
        final int roomLimit = this.roomEnvironmentVariables.userRoomsLimit; // TODO: Check user HC subscription

        session.send(new CanCreateRoomComposer(canCreateRoom, roomLimit));
    }
}
