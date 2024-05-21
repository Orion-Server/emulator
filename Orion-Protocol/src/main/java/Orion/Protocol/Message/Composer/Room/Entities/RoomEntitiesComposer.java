package Orion.Protocol.Message.Composer.Room.Entities;

import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.Collection;
import java.util.List;

public class RoomEntitiesComposer extends MessageComposer {
    public RoomEntitiesComposer(Collection<IRoomEntity> entities) {
        super(ComposerHeaders.RoomEntitiesComposer);

        appendInt(entities.size());

        for(final IRoomEntity entity : entities) {
            entity.write(this);
        }
    }

    public RoomEntitiesComposer(IRoomEntity entity) {
        this(List.of(entity));
    }
}
