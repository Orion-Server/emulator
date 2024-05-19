package Orion.Protocol.Message.Composer.Room.Object;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.Map;

public class RoomFloorObjectsComposer extends MessageComposer {
    public RoomFloorObjectsComposer(final IRoom room) {
        super(ComposerHeaders.RoomFloorObjectsComposer);

        appendInt(room.getItemsComponent().getOwnerNames().size());

        for (final Map.Entry<Integer, String> itemOwner : room.getItemsComponent().getOwnerNames().entrySet()) {
            appendInt(itemOwner.getKey());
            appendString(itemOwner.getValue());
        }

        appendInt(room.getItemsComponent().getFloorItems().size());

        for (final IRoomFloorItem item : room.getItemsComponent().getFloorItems().values()) {
            item.write(this);
        }
    }
}
