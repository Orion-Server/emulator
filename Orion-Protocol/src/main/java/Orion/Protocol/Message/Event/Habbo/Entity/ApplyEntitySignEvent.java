package Orion.Protocol.Message.Event.Habbo.Entity;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.Room.Object.Entity.Enum.EntitySignType;
import Orion.Api.Server.Game.Room.Object.Entity.Enum.RoomEntityStatus;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class ApplyEntitySignEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.ApplyEntitySignEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        if(!session.getHabbo().isInRoom()) return;

        final EntitySignType signType = EntitySignType.fromValue(event.readInt());

        if(signType == null) return;

        session.getHabbo().getEntity().setStatus(RoomEntityStatus.SIGN, String.valueOf(signType.get()));
        session.getHabbo().getEntity().setNeedsUpdate(true);
    }
}
