package Orion.Writer.Room.Object.Item;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Object.Item.IRoomWallItem;

public abstract class RoomWallItemWriter {
    public static void write(
            final IRoomWallItem item,
            final IMessageComposer composer
    ) {

    }
}
