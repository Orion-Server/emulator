package Orion.Game.Room.Object.Entity.Component;

import Orion.Api.Server.Game.Room.Data.Model.IRoomTile;
import Orion.Api.Server.Game.Room.Object.Entity.Component.IEntityWalkComponent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

public class EntityWalkComponent implements IEntityWalkComponent {
    private ConcurrentLinkedQueue<IRoomTile> processingPath;

    private ConcurrentSkipListSet<IRoomTile> walkingPath;

    public EntityWalkComponent() {

    }
}
