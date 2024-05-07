package Orion.Writer.Room;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.IRoom;

public abstract class RoomWriter {
    public static void write(
            final IRoom room,
            final IMessageComposer composer
    ) {
        composer.appendInt(room.getData().getId());
        composer.appendString(room.getData().getName());

        composer.appendInt(room.getData().isPublic() ? 0 : room.getData().getOwnerId());
        composer.appendString(room.getData().isPublic() ? "" : room.getData().getOwnerName());

        composer.appendInt(room.getData().getAccessState().getState());
        composer.appendInt(0);
        composer.appendInt(room.getData().getMaxUsers());
        composer.appendString(room.getData().getDescription());
        composer.appendInt(room.getData().getTradeMode());
        composer.appendInt(room.getData().getScore());
        composer.appendInt(0);
        composer.appendInt(room.getData().getCategoryId());

        composer.appendInt(room.getData().getTags().size());

        room.getData().getTags().forEach(composer::appendString);

        int base = 0;

        if (room.getData().getGuildId() > 0) base |= 2;
        if (room.getData().isPromoted()) base |= 4;
        if (!room.getData().isPublic()) base |= 8;
        if (!room.getData().allowPets()) base |= 16;

        composer.appendInt(base);

        // TODO: Write room guild

        // TODO: Write room promotion
    }
}
