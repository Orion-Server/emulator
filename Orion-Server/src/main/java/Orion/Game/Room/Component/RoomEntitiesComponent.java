package Orion.Game.Room.Component;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.Component.IRoomEntitiesComponent;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;
import Orion.Game.Room.Object.Entity.HabboEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomEntitiesComponent implements IRoomEntitiesComponent {
    private final Logger logger = LogManager.getLogger();

    private final IRoom room;

    private final AtomicInteger virtualIdPointer;

    private final ConcurrentHashMap<Integer, IRoomEntity> roomEntities;

    private final ConcurrentHashMap<Integer, IHabboEntity> habboEntities;

    public RoomEntitiesComponent(final IRoom room) {
        this.room = room;
        this.virtualIdPointer = new AtomicInteger();

        this.roomEntities = new ConcurrentHashMap<>();
        this.habboEntities = new ConcurrentHashMap<>();
    }

    @Override
    public void initialize() {

    }

    @Override
    public int getCurrentVirtualId() {
        return this.virtualIdPointer.get();
    }

    @Override
    public int getNextVirtualId() {
        return this.virtualIdPointer.incrementAndGet();
    }

    @Override
    public List<IHabboEntity> getHabboEntities() {
        return this.habboEntities.values().stream().toList();
    }

    @Override
    public List<IHabboEntity> getHabbosWithRights() {
        final List<IHabboEntity> habbosWithRights = new ArrayList<>();

        for (final IHabboEntity entity : this.habboEntities.values()) {
            if(!this.room.getRightsComponent().hasRights(entity.getHabbo())) continue;

            habbosWithRights.add(entity);
        }

        return habbosWithRights;
    }

    @Override
    public List<IRoomEntity> getRoomEntities() {
        return this.roomEntities.values().stream().toList();
    }

    @Override
    public IHabboEntity createHabboEntity(final IHabbo habbo) {
        final IHabboEntity entity = new HabboEntity(this.getNextVirtualId(), habbo, this.room);

        this.roomEntities.put(entity.getVirtualId(), entity);
        this.habboEntities.put(entity.getVirtualId(), entity);

        return entity;
    }

    @Override
    public void addEntity(final IRoomEntity entity) {
        this.roomEntities.put(entity.getVirtualId(), entity);
    }

    @Override
    public void removeEntity(final IRoomEntity entity) {
        this.roomEntities.remove(entity.getVirtualId());

        if(entity instanceof IHabboEntity) {
            this.habboEntities.remove(entity.getVirtualId());
        }

        this.room.onEntityRemoved(entity);
    }

    @Override
    public void dispose() {

    }
}
