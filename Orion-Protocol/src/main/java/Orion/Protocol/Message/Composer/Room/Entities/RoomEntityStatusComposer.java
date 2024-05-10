package Orion.Protocol.Message.Composer.Room.Entities;

import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.List;

public class RoomEntityStatusComposer extends MessageComposer {
    public RoomEntityStatusComposer(final IRoomEntity entity) {
        super(ComposerHeaders.RoomEntityStatusComposer);

        appendInt(1);

        this.composeEntity(entity);
    }

    public RoomEntityStatusComposer(final List<IRoomEntity> entities) {
        super(ComposerHeaders.RoomEntityStatusComposer);

        appendInt(entities.size());

        for (final IRoomEntity entity : entities) {
            this.composeEntity(entity);
        }
    }

    private void composeEntity(final IRoomEntity entity) {
        appendInt(entity.getVirtualId());
        appendInt(entity.getPosition().getX());
        appendInt(entity.getPosition().getY());
        appendString(String.valueOf(entity.getPosition().getZ()));

        appendInt(entity.getHeadRotation());
        appendInt(entity.getBodyRotation());

        appendString("/");
    }
}
