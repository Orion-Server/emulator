package Orion.Game.Room.Object.Pathfinder;

import Orion.Api.Server.Game.Room.Enums.RoomDiagonalType;
import Orion.Api.Server.Game.Room.Object.IRoomObject;
import Orion.Api.Server.Game.Util.Position;
import com.google.common.collect.MinMaxPriorityQueue;

import java.util.ArrayList;
import java.util.List;

public class Pathfinder {
    public static final Position[] movePoints = {
            new Position(0, -1),
            new Position(1, 0),
            new Position(0, 1),
            new Position(-1, 0)
    };

    public static final Position[] diagonalMovePoints = {
            new Position(-1, -1),
            new Position(0, -1),
            new Position(1, 1),
            new Position(0, 1),
            new Position(1, -1),
            new Position(1, 0),
            new Position(-1, 1),
            new Position(-1, 0)
    };

    public List<Position> findPath(
            final IRoomObject object,
            final Position endPosition
    ) {
        return this.findPath(object, endPosition, RoomDiagonalType.OFFICIAL, false, true);
    }

    public List<Position> findPath(
            final IRoomObject object,
            final Position endPosition,
            final RoomDiagonalType diagonalType,
            final boolean isRetry,
            boolean firstGeneration
    ) {
        final List<Position> paths = new ArrayList<>();

        if (object.getPosition().equals(endPosition)) {
            return paths;
        }

        return paths;
    }

    public PathfinderNode findReversedPath(
            final IRoomObject object,
            final Position endPosition,
            final RoomDiagonalType diagonalType,
            final boolean isRetry,
            final boolean firstGeneration
    ) {
        final MinMaxPriorityQueue<PathfinderNode> openList = MinMaxPriorityQueue.maximumSize(256).create();

        final PathfinderNode[][] map = new PathfinderNode[object.getRoom().getModel().getMapSizeX()][object.getRoom().getModel().getMapSizeY()];

        PathfinderNode node;
        Position tmpPosition;

        int cost;

        PathfinderNode current = new PathfinderNode(object.getPosition());
        current.setCost(0);

        final PathfinderNode finish = new PathfinderNode(endPosition);

        map[current.getPosition().getX()][current.getPosition().getY()] = current;
        openList.add(current);

        return null;
    }
}
