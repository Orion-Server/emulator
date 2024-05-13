package Orion.Game.Room.Object.Entity.Factory;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;
import Orion.Api.Server.Game.Room.Object.Pathfinder.IPathfinder;
import Orion.Game.Room.Object.Entity.HabboEntity;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class HabboEntityFactory {
    @Inject
    private IPathfinder pathfinder;

    public IHabboEntity create(final IRoom room, final IHabbo habbo) {
        final IHabboEntity entity = new HabboEntity(
                room.getEntitiesComponent().getNextVirtualId(),
                habbo,
                room
        );

        entity.getWalkComponent().setPathfinder(this.pathfinder);

        entity.setHabbo(habbo);
        habbo.setEntity(entity);

        room.getEntitiesComponent().addEntity(entity);

        return entity;
    }
}
