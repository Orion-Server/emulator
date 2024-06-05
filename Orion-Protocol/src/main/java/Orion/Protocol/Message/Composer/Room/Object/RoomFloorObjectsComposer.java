package Orion.Protocol.Message.Composer.Room.Object;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.Map;

public class RoomFloorObjectsComposer extends Composer {
    private final IRoom room;

    public RoomFloorObjectsComposer(final IRoom room) {
        this.room = room;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomFloorObjectsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.room.getItemsComponent().getOwnerNames().size());

        for (final Map.Entry<Integer, String> itemOwner : this.room.getItemsComponent().getOwnerNames().entrySet()) {
            msg.appendInt(itemOwner.getKey());
            msg.appendString(itemOwner.getValue());
        }

        msg.appendInt(this.room.getItemsComponent().getFloorItems().size());

        for (final IRoomFloorItem item : this.room.getItemsComponent().getFloorItems().values()) {
            item.write(msg);
        }
    }
}
