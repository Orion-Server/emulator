package Orion.Protocol.Message.Event.Room.Item;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Item.Enum.FurnitureMovementError;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Api.Server.Game.Util.Alert.MiddleAlertType;
import Orion.Api.Server.Game.Util.Position;
import Orion.Protocol.Message.Composer.Alerts.MiddleAlertComposer;
import Orion.Protocol.Message.Composer.Room.Object.UpdateFloorItemComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestItemMovementEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.RequestItemMovementEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        if(!session.getHabbo().isInRoom()) return;

        final IRoom room = session.getHabbo().getEntity().getRoom();

        if(room == null) return;

        final int itemId = event.readInt();
        final int newX = event.readInt();
        final int newY = event.readInt();
        final int newRotation = event.readInt();

        final IRoomFloorItem item = room.getItemsComponent().getFloorItems().get(itemId);

        if(item == null) return;

        if(!room.getRightsComponent().hasRights(session.getHabbo()) && !session.getHabbo().getPermission().hasAccountPermission("moverotate")) {
            session.send(
                    new MiddleAlertComposer(MiddleAlertType.FURNITURE_PLACEMENT_ERROR, FurnitureMovementError.NO_RIGHTS),
                    new UpdateFloorItemComposer(item)
            );
            return;
        }

        final FurnitureMovementError error = room.getItemsComponent().applyItemMovement(item, new Position(newX, newY), newRotation);

        if(error.equals(FurnitureMovementError.NONE)) return;

        session.send(
                new MiddleAlertComposer(MiddleAlertType.FURNITURE_PLACEMENT_ERROR, error),
                new UpdateFloorItemComposer(item)
        );
    }
}
