package Orion.Protocol.Message.Composer.Room.Entities;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Object.Entity.Enum.RoomEntityStatus;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class RoomEntityStatusComposer extends Composer {
    private final Collection<IRoomEntity> entities;

    public RoomEntityStatusComposer(final Collection<IRoomEntity> entities) {
        this.entities = entities;
    }

    public RoomEntityStatusComposer(final IRoomEntity entity) {
        this(List.of(entity));
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomEntityStatusComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.entities.size());

        for (final IRoomEntity entity : this.entities) {
            this.composeEntity(entity, msg);
        }
    }

    private void composeEntity(final IRoomEntity entity, IMessageComposer msg) {
        msg.appendInt(entity.getVirtualId());
        msg.appendInt(entity.getPosition().getX());
        msg.appendInt(entity.getPosition().getY());
        msg.appendString(String.valueOf(entity.getPosition().getZ()));

        msg.appendInt(entity.getHeadRotation());
        msg.appendInt(entity.getBodyRotation());

        final StringBuilder status = new StringBuilder("/");

        for (final Map.Entry<RoomEntityStatus, String> entry : entity.getAllStatus().entrySet()) {
            status.append(entry.getKey().get()).append(" ").append(entry.getValue()).append("/");
        }

        msg.appendString(status.toString());
    }
}
