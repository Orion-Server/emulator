package Orion.Game.Room.Component;

import Orion.Api.Server.Game.Room.Component.IRoomEntitiesComponent;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;
import Orion.Game.Room.Object.Entity.HabboEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomEntitiesComponent implements IRoomEntitiesComponent {
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
    public int getHabboEntitiesCount() {
        return this.habboEntities.size();
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
    public void addEntity(final IRoomEntity entity) {
        this.roomEntities.put(entity.getVirtualId(), entity);

        if(entity instanceof IHabboEntity) {
            this.habboEntities.put(entity.getVirtualId(), (HabboEntity) entity);
        }
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
