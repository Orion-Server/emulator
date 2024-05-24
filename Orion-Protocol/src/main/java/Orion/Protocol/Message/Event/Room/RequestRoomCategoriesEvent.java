package Orion.Protocol.Message.Event.Room;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Protocol.Message.Composer.Room.RoomCategoriesComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestRoomCategoriesEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.RequestRoomCategoriesEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        session.send(new RoomCategoriesComposer());
    }
}