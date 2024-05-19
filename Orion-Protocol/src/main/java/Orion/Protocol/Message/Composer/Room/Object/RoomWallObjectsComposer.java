package Orion.Protocol.Message.Composer.Room.Object;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Item.IRoomWallItem;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.Map;

public class RoomWallObjectsComposer extends MessageComposer {
    public RoomWallObjectsComposer(final IRoom room) {
        super(ComposerHeaders.RoomWallObjectsComposer);

        appendInt(room.getItemsComponent().getOwnerNames().size());

        for (final Map.Entry<Integer, String> itemOwner : room.getItemsComponent().getOwnerNames().entrySet()) {
            appendInt(itemOwner.getKey());
            appendString(itemOwner.getValue());
        }

        appendInt(room.getItemsComponent().getWallItems().size());

        for (final IRoomWallItem item : room.getItemsComponent().getWallItems().values()) {
            item.write(this);
        }
    }
}
