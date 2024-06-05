package Orion.Protocol.Message.Composer.Room.Object;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Item.IRoomWallItem;
import Orion.Networking.Message.Composer;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.Map;

public class RoomWallObjectsComposer extends Composer {
    private final IRoom room;

    public RoomWallObjectsComposer(final IRoom room) {
        this.room = room;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomWallObjectsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.room.getItemsComponent().getOwnerNames().size());

        for (final Map.Entry<Integer, String> itemOwner : this.room.getItemsComponent().getOwnerNames().entrySet()) {
            msg.appendInt(itemOwner.getKey());
            msg.appendString(itemOwner.getValue());
        }

        msg.appendInt(this.room.getItemsComponent().getWallItems().size());

        for (final IRoomWallItem item : this.room.getItemsComponent().getWallItems().values()) {
            item.write(msg);
        }
    }
}
