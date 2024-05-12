package Orion.Game.Room.Object.Entity.Component;

import Orion.Api.Server.Game.Room.Data.Model.IRoomTile;
import Orion.Api.Server.Game.Room.Object.Entity.Component.IEntityWalkComponent;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Room.Object.Pathfinder.IPathfinder;
import Orion.Api.Server.Game.Util.Position;

import java.util.ArrayList;
import java.util.List;

public class EntityWalkComponent implements IEntityWalkComponent {
    private final List<Position> walkingPath;

    private final List<Position> processingPath;

    private final IRoomEntity entity;

    public EntityWalkComponent(final IRoomEntity entity) {
        this.walkingPath = new ArrayList<>();
        this.processingPath = new ArrayList<>();

        this.entity = entity;
    }

    @Override
    public List<Position> getProcessingPath() {
        return this.processingPath;
    }

    @Override
    public List<Position> getWalkingPath() {
        return this.walkingPath;
    }

    @Override
    public void walkToPosition(final IPathfinder pathfinder, int x, int y) {
        if(this.entity.getPosition().equals(x, y)) return;

        final IRoomTile tile = this.entity.getRoom().getMappingComponent().getTile(x, y);

        if(tile == null) return;

        final List<Position> walkingPath = pathfinder.findPath(
                this.entity,
                tile.getPosition(),
                this.entity.getRoom().getData().getDiagonalType()
        );

        this.walkingPath.clear();
        this.walkingPath.addAll(walkingPath);
    }
}
