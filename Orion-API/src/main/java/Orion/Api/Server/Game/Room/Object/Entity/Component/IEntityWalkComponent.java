package Orion.Api.Server.Game.Room.Object.Entity.Component;

import Orion.Api.Server.Game.Room.Object.Pathfinder.IPathfinder;
import Orion.Api.Server.Game.Util.Position;

import java.util.List;

public interface IEntityWalkComponent {
    List<Position> getProcessingPath();

    List<Position> getWalkingPath();

    void walkToPosition(final IPathfinder pathfinder, int x, int y);
}
