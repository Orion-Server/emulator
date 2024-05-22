package Orion.Protocol.Message.Event.Room.Item;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Room.Item.ToggleFloorItemStateEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ToggleFloorItemStateEvent implements IMessageEventHandler {
    @Inject
    private ToggleFloorItemStateEventParser parser;

    @Override
    public int getId() {
        return EventHeaders.ToggleFloorItemStateEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    @Override
    public void handle(ISession session) {
        if(!session.getHabbo().isInRoom()) return;

        final IRoomFloorItem floorItem = session.getHabbo().getEntity().getRoom().getItemsComponent().getFloorItemByVirtualId(this.parser.itemId);

        if(floorItem == null) return;

        floorItem.getInteraction().onInteract(session.getHabbo().getEntity(), this.parser.state);
    }
}
