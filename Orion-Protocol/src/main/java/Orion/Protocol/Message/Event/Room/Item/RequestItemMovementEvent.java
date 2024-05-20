package Orion.Protocol.Message.Event.Room.Item;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Item.Enum.FurnitureMovementError;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Api.Server.Game.Util.Alert.MiddleAlertType;
import Orion.Api.Server.Game.Util.Position;
import Orion.Protocol.Message.Composer.Alerts.MiddleAlertComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Room.Item.RequestItemMovementEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestItemMovementEvent implements IMessageEventHandler {
    @Inject
    private RequestItemMovementEventParser parser;

    @Override
    public int getId() {
        return EventHeaders.RequestItemMovementEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    @Override
    public void handle(ISession session) {
        if(!session.getHabbo().isInRoom()) return;

        final IRoom room = session.getHabbo().getEntity().getRoom();

        if(room == null) return;

        final IRoomFloorItem item = room.getItemsComponent().getFloorItems().get(this.parser.itemId);

        if(item == null) return;

        final FurnitureMovementError error = room.getItemsComponent().moveFloorItem(
                item,
                new Position(this.parser.newX, this.parser.newY),
                this.parser.newRotation
        );

        if(!error.equals(FurnitureMovementError.NONE)) {
            session.send(new MiddleAlertComposer(MiddleAlertType.FURNITURE_PLACEMENT_ERROR, error));
            return;
        }

        System.out.println("Moved");
    }
}
