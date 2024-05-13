package Orion.Api.Server.Game.Room.Object.Entity.Component;

import Orion.Api.Server.Game.Room.Object.Pathfinder.IPathfinder;
import Orion.Api.Server.Game.Util.Position;

import java.util.List;

public interface IEntityWalkComponent {
    void setProcessingPath(final List<Position> processingPath);

    List<Position> getProcessingPath();

    void clearProcessingPath();

    void setWalkingPath(final List<Position> walkingPath);

    List<Position> getWalkingPath();

    void clearWalkingPath();

    void walkToPosition(int x, int y);

    void setPathfinder(final IPathfinder pathfinder);
}
