package Orion.Protocol.Message.Composer.Room.Entities;

import Orion.Api.Server.Game.Room.Object.Entity.Enum.RoomEntityStatus;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class RoomEntityStatusComposer extends MessageComposer {
    public RoomEntityStatusComposer(final Collection<IRoomEntity> entities) {
        super(ComposerHeaders.RoomEntityStatusComposer);

        appendInt(entities.size());

        for (final IRoomEntity entity : entities) {
            this.composeEntity(entity);
        }
    }

    public RoomEntityStatusComposer(final IRoomEntity entity) {
        this(List.of(entity));
    }

    private void composeEntity(final IRoomEntity entity) {
        appendInt(entity.getVirtualId());
        appendInt(entity.getPosition().getX());
        appendInt(entity.getPosition().getY());
        appendString(String.valueOf(entity.getPosition().getZ()));

        appendInt(entity.getHeadRotation());
        appendInt(entity.getBodyRotation());

        final StringBuilder status = new StringBuilder("/");

        for (final Map.Entry<RoomEntityStatus, String> entry : entity.getAllStatus().entrySet()) {
            status.append(entry.getKey().get()).append(" ").append(entry.getValue()).append("/");
        }

        appendString(status.toString());
    }
}
