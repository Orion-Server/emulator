package Orion.Protocol.Message.Composer.Room.Entities;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Networking.Message.Composer;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.Collection;
import java.util.List;

public class RoomEntitiesComposer extends Composer {
    private final Collection<IRoomEntity> entities;

    public RoomEntitiesComposer(final Collection<IRoomEntity> entities) {
        this.entities = entities;
    }

    public RoomEntitiesComposer(IRoomEntity entity) {
        this(List.of(entity));
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomEntitiesComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.entities.size());

        for(final IRoomEntity entity : this.entities) {
            entity.write(msg);
        }
    }
}
