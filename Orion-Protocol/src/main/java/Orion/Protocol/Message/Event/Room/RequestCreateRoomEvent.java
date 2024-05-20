package Orion.Protocol.Message.Event.Room;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Room.Handler.ICreateRoomHandler;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Protocol.Message.Composer.Room.RoomCreatedComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Room.RequestCreateRoomEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestCreateRoomEvent implements IMessageEventHandler {
    @Inject
    private RequestCreateRoomEventParser parser;

    @Inject
    private ICreateRoomHandler createRoomHandler;

    @Override
    public int getId() {
        return EventHeaders.RequestCreateRoomEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    @Override
    public void handle(ISession session) {
        final IRoom room = this.createRoomHandler.createRoom(
                session.getHabbo(),
                this.parser.name,
                this.parser.description,
                this.parser.modelName,
                this.parser.categoryId,
                this.parser.maxUsers,
                this.parser.tradeType
        );

        if(room == null) return;

        session.send(new RoomCreatedComposer(room.getData().getId(), room.getData().getName()));

    }
}
