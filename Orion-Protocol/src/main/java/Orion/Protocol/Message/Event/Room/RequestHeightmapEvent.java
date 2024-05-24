package Orion.Protocol.Message.Event.Room;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.Room.Handler.IJoinRoomHandler;
import Orion.Protocol.Message.Composer.HotelView.GoToHotelViewComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestHeightmapEvent implements IMessageEventHandler {
    @Inject
    private IJoinRoomHandler joinRoomHandler;

    @Override
    public int getId() {
        return EventHeaders.RequestHeightmapEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        if (session.getHabbo().getEntity() == null || session.getHabbo().getEntity().getRoom() == null) {
            session.send(new GoToHotelViewComposer());
            return;
        }

        joinRoomHandler.finalizeRoomEnter(
                session.getHabbo().getEntity().getRoom(),
                session.getHabbo()
        );
    }
}
