package Orion.Protocol.Message.Event.Room;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Room.Handler.IJoinRoomHandler;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.IRoomManager;
import Orion.Protocol.Message.Composer.HotelView.GoToHotelViewComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Room.RequestRoomLoadParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestRoomLoadEvent implements IMessageEventHandler {
    @Inject
    private IRoomManager roomManager;

    @Inject
    private RequestRoomLoadParser parser;

    @Inject
    private IJoinRoomHandler joinRoomHandler;

    @Override
    public int getId() {
        return EventHeaders.RequestRoomLoadEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    @Override
    public void handle(ISession session) {
        final IRoom room = this.roomManager.getRoomById(this.parser.roomId);

        if(room == null) {
            session.send(new GoToHotelViewComposer());
            return;
        }

        joinRoomHandler.prepareRoom(room, session.getHabbo(), this.parser.password);
    }
}
