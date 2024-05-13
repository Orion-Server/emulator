package Orion.Protocol.Message.Composer.Room.Entities;

import Orion.Api.Server.Game.Room.Object.Entity.Enum.RoomEntityStatus;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;
import gnu.trove.set.hash.THashSet;

import java.util.List;
import java.util.Map;

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

    public RoomEntityStatusComposer(final THashSet<IRoomEntity> entities) {
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

        StringBuilder status = new StringBuilder("/");

        for (final Map.Entry<RoomEntityStatus, String> entry : entity.getAllStatus().entrySet()) {
            status.append(STR."\{entry.getKey().get()} \{entry.getValue()}/");
        }

        appendString(status.toString());
    }
}
