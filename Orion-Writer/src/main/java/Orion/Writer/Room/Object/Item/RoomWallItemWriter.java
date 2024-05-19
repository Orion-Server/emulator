package Orion.Writer.Room.Object.Item;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Object.Item.IRoomWallItem;

public abstract class RoomWallItemWriter {
    public static void write(
            final IRoomWallItem item,
            final IMessageComposer composer
    ) {
        composer.appendString(STR."\{item.getVirtualId()}");
        composer.appendInt(item.getDefinition().getSpriteId());
        composer.appendString(item.getData().getWallPosition());

        composer.appendString(item.getData().getExtraData());
        composer.appendInt(-1);

        composer.appendInt(item.getDefinition().getInteractionModesCount() > 1 ? 1 : 0);
        composer.appendInt(item.getRoom().getData().getOwnerId());
    }
}
