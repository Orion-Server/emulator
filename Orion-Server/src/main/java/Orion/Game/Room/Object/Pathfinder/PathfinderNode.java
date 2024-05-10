package Orion.Game.Room.Object.Pathfinder;

import Orion.Api.Server.Game.Util.Position;

public class PathfinderNode implements Comparable<PathfinderNode> {
    private Position position;
    private PathfinderNode nextNode;

    private boolean inOpen = false;
    private boolean inClosed = false;

    private Integer cost = Integer.MAX_VALUE;

    public PathfinderNode(Position current) {
        this.position = current;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public PathfinderNode getNextNode() {
        return this.nextNode;
    }

    public void setNextNode(PathfinderNode nextNode) {
        this.nextNode = nextNode;
    }

    public Integer getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isInOpen() {
        return this.inOpen;
    }

    public void setInOpen(boolean inOpen) {
        this.inOpen = inOpen;
    }

    public boolean isInClosed() {
        return this.inClosed;
    }

    public void setInClosed(boolean inClosed) {
        this.inClosed = inClosed;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PathfinderNode) && ((PathfinderNode) obj).getPosition().equals(this.position);
    }

    public boolean equals(PathfinderNode node) {
        return node.getPosition().equals(this.position);
    }

    @Override
    public int compareTo(PathfinderNode o) {
        return this.getCost().compareTo(o.getCost());
    }
}
