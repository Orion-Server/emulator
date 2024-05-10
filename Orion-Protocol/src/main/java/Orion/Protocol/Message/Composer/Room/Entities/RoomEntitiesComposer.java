package Orion.Protocol.Message.Composer.Room.Entities;

import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.List;

public class RoomEntitiesComposer extends MessageComposer {
    public RoomEntitiesComposer(IRoomEntity entities) {
        super(ComposerHeaders.RoomEntitiesComposer);

        appendInt(1);
        entities.write(this);
    }

    public RoomEntitiesComposer(final List<IRoomEntity> entities) {
        super(ComposerHeaders.RoomEntitiesComposer);

        appendInt(entities.size());

        for(final IRoomEntity entity : entities) {
            entity.write(this);
        }
    }
}
