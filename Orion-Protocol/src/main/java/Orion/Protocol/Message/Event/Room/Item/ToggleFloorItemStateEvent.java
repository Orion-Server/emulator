package Orion.Protocol.Message.Event.Room.Item;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class ToggleFloorItemStateEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.ToggleFloorItemStateEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        if(!session.getHabbo().isInRoom()) return;

        final int itemId = event.readInt();
        final int state = event.readInt();

        final IRoomFloorItem floorItem = session.getHabbo().getEntity().getRoom().getItemsComponent().getFloorItemByVirtualId(itemId);

        if(floorItem == null) return;

        floorItem.getInteraction().onInteract(session.getHabbo().getEntity(), state);
    }
}
