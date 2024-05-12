package Orion.Game.Room.Object.Pathfinder;

import Orion.Api.Server.Game.Room.Enums.RoomDiagonalType;
import Orion.Api.Server.Game.Room.Object.IRoomObject;
import Orion.Api.Server.Game.Room.Object.Pathfinder.IPathfinder;
import Orion.Api.Server.Game.Util.Position;
import com.google.common.collect.Lists;
import com.google.common.collect.MinMaxPriorityQueue;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class Pathfinder implements IPathfinder {
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

    @Override
    public List<Position> findPath(final IRoomObject object, final Position endPosition) {
        return this.findPath(object, endPosition, RoomDiagonalType.OFFICIAL, false, true);
    }

    public List<Position> findPath(final IRoomObject object, final Position endPosition, RoomDiagonalType diagonalType) {
        return this.findPath(object, endPosition, diagonalType, false, true);
    }

    @Override
    public List<Position> findPath(
            final IRoomObject object,
            final Position endPosition,
            final RoomDiagonalType diagonalType,
            final boolean isRetry,
            boolean firstGeneration
    ) {
        final List<Position> paths = new ArrayList<>();
        PathfinderNode nodes = this.findReversedPath(object, endPosition, diagonalType, isRetry, firstGeneration);

        if(nodes == null) return paths;

        while(nodes.getNextNode() != null) {
            paths.add(nodes.getPosition());
            nodes = nodes.getNextNode();
        }

        return Lists.reverse(paths);
    }

    @SuppressWarnings("UnstableApiUsage")
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
        openList.add(current);

        final PathfinderNode finish = new PathfinderNode(endPosition);

        map[current.getPosition().getX()][current.getPosition().getY()] = current;

        while(!openList.isEmpty()) {
            current = openList.pollFirst();
            current.setInClosed(true);

            for(int i = 0; i < this.getMovePointsCount(diagonalType); i++) {
                tmpPosition = current.getPosition().add(this.getMovePoint(diagonalType, i));

                final boolean isFinalMove = tmpPosition.equals(finish.getPosition());

                if(!object.getRoom().getMappingComponent().isValidPosition(object, current.getPosition(), tmpPosition, isFinalMove, isRetry, firstGeneration)) continue;

                try {
                    if(map[tmpPosition.getX()][tmpPosition.getY()] == null) {
                        node = new PathfinderNode(tmpPosition);
                        map[tmpPosition.getX()][tmpPosition.getY()] = node;
                    } else {
                        node = map[tmpPosition.getX()][tmpPosition.getY()];
                    }
                } catch (Exception e) { continue; }

                if(node.isInClosed()) continue;

                cost = current.getCost() + node.getPosition().getDistanceSquared(endPosition);

                if(cost < node.getCost()) {
                    node.setCost(cost);
                    node.setNextNode(current);
                }

                if(node.isInOpen()) continue;

                if(node.getPosition().equals(finish.getPosition())) {
                    node.setNextNode(current);
                    return node;
                }

                node.setInOpen(true);
                openList.add(node);
            }
        }

        return null;
    }

    private int getMovePointsCount(RoomDiagonalType diagonalType) {
        return diagonalType == RoomDiagonalType.DISABLED ? Pathfinder.movePoints.length : Pathfinder.diagonalMovePoints.length;
    }

    private Position getMovePoint(RoomDiagonalType diagonalType, int index) {
        return diagonalType == RoomDiagonalType.DISABLED ? Pathfinder.movePoints[index] : Pathfinder.diagonalMovePoints[index];
    }
}
