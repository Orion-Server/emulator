package Orion.Api.Server.Game.Room.Object.Pathfinder;

import Orion.Api.Server.Game.Room.Enums.RoomDiagonalType;
import Orion.Api.Server.Game.Room.Object.IRoomObject;
import Orion.Api.Server.Game.Util.Position;

import java.util.List;

public interface IPathfinder {
    List<Position> findPath(final IRoomObject object, final Position endPosition);

    List<Position> findPath(final IRoomObject object, final Position endPosition, RoomDiagonalType diagonalType);

    List<Position> findPath(
            final IRoomObject object,
            final Position endPosition,
            final RoomDiagonalType diagonalType,
            final boolean isRetry,
            boolean firstGeneration
    );
}
