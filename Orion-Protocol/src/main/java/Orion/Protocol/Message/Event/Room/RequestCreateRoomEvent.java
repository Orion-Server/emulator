package Orion.Protocol.Message.Event.Room;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.Room.Enums.RoomCategoryType;
import Orion.Api.Server.Game.Room.Enums.RoomTradeType;
import Orion.Api.Server.Game.Room.Handler.ICreateRoomHandler;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Protocol.Message.Composer.Room.RoomCreatedComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestCreateRoomEvent implements IMessageEventHandler {
    @Inject
    private ICreateRoomHandler createRoomHandler;

    @Override
    public int getId() {
        return EventHeaders.RequestCreateRoomEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        final String name = event.readString();
        final String description = event.readString();
        final String modelName = event.readString();
        final RoomCategoryType categoryId = RoomCategoryType.fromCategoryId(event.readInt());
        final int maxUsers = event.readInt();
        final RoomTradeType tradeType = RoomTradeType.fromValue(event.readInt());

        final IRoom room = this.createRoomHandler.createRoom(
                session.getHabbo(), name, description, modelName, categoryId, maxUsers, tradeType
        );

        if(room == null) return;

        session.send(new RoomCreatedComposer(room.getData().getId(), room.getData().getName()));
    }
}
