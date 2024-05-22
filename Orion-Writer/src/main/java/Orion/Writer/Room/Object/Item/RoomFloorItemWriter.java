package Orion.Writer.Room.Object.Item;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;

public abstract class RoomFloorItemWriter {
    public static void write(final IRoomFloorItem item, final IMessageComposer composer) {
        composer.appendInt(item.getVirtualId());
        composer.appendInt(item.getDefinition().getSpriteId());
        composer.appendInt(item.getPosition().getX());
        composer.appendInt(item.getPosition().getY());
        composer.appendInt(item.getData().getRotation());
        composer.appendString(item.getPosition().getZ());
        composer.appendString(item.getDefinition().getStackHeight());
        composer.appendInt(1); // TODO: Gift, song, etc

        composer.appendInt(0); // TODO: Implement extra data
        composer.appendString(item.getData().getExtraData()); // TODO: Implement extra data

        if(item.getData().getLimitedData() != null) {
            composer.appendInt(item.getData().getLimitedData().getLimitedEditionNumber());
            composer.appendInt(item.getData().getLimitedData().getLimitedEditionTotal());
        }

        composer.appendInt(-1);
        composer.appendInt(2); // Item usable policy
        composer.appendInt(item.getData().getOwnerId());
    }
}
