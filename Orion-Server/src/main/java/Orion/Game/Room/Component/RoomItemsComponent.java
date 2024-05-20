package Orion.Game.Room.Component;

import Orion.Api.Server.Game.Room.Component.IRoomItemsComponent;
import Orion.Api.Server.Game.Room.Data.Model.IRoomTile;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Item.Enum.FurnitureMovementError;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Api.Server.Game.Room.Object.Item.IRoomItem;
import Orion.Api.Server.Game.Room.Object.Item.IRoomWallItem;
import Orion.Api.Server.Game.Room.Object.Item.ItemDefinitionType;
import Orion.Api.Server.Game.Util.Position;
import Orion.Api.Storage.Repository.Room.IRoomItemsRepository;
import Orion.Game.Room.Object.Item.Factory.RoomItemFactory;
import com.google.inject.Inject;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomItemsComponent implements IRoomItemsComponent {
    private final IRoom room;

    @Inject
    private IRoomItemsRepository repository;

    @Inject
    private RoomItemFactory itemFactory;

    private final ConcurrentHashMap<Integer, IRoomFloorItem> floorItems;

    private final ConcurrentHashMap<Integer, IRoomWallItem> wallItems;

    private final ConcurrentHashMap<Integer, String> ownerNames;

    private final AtomicInteger virtualIdPointer;

    public RoomItemsComponent(final IRoom room) {
        this.room = room;

        this.wallItems = new ConcurrentHashMap<>();
        this.floorItems = new ConcurrentHashMap<>();
        this.ownerNames = new ConcurrentHashMap<>();

        this.virtualIdPointer = new AtomicInteger(0);
    }

    @Override
    public void initialize() {
        this.loadRoomItems();
    }

    @Override
    public ConcurrentHashMap<Integer, IRoomWallItem> getWallItems() {
        return this.wallItems;
    }

    @Override
    public ConcurrentHashMap<Integer, IRoomFloorItem> getFloorItems() {
        return this.floorItems;
    }

    @Override
    public ConcurrentHashMap<Integer, String> getOwnerNames() {
        return this.ownerNames;
    }

    @Override
    public FurnitureMovementError moveFloorItem(final IRoomFloorItem item, Position position, int rotation) {
        if(this.floorItems.size() + this.wallItems.size() >= 2500) {
            return FurnitureMovementError.MAX_ITEMS;
        }

        if(position.equals(item.getPosition()) && rotation == item.getData().getRotation()) {
            return FurnitureMovementError.NONE;
        }

        final IRoomTile tile = this.room.getMappingComponent().getTile(position);

        if(tile == null) {
            return FurnitureMovementError.INVALID_MOVE;
        }

        if(!tile.canPlaceItems()) {
            return FurnitureMovementError.CANT_STACK;
        }

        if(!tile.getEntities().isEmpty()) {
            return FurnitureMovementError.TILE_HAS_HABBOS;
        }

        if(tile.getTopItem() != null && !tile.getTopItem().getDefinition().isAllowStack()) {
            return FurnitureMovementError.CANT_STACK;
        }

        return FurnitureMovementError.NONE;
    }

    private void loadRoomItems() {
        this.repository.loadItemsByRoomId(result -> {
            if(result == null) return;

            final IRoomItem loadedItem = this.itemFactory.create(this.virtualIdPointer.getAndIncrement(), result, this.room);

            if(loadedItem == null) return;

            if(loadedItem.getDefinition().getType() == ItemDefinitionType.FLOOR) {
                this.floorItems.put(loadedItem.getVirtualId(), (IRoomFloorItem) loadedItem);
            } else {
                this.wallItems.put(loadedItem.getVirtualId(), (IRoomWallItem) loadedItem);
            }

            if(loadedItem.getData().getOwnerId() != 0) {
                this.ownerNames.putIfAbsent(loadedItem.getData().getOwnerId(), result.getString("owner_name"));
            }

            if(!loadedItem.getDefinition().getType().equals(ItemDefinitionType.FLOOR)) return;

            if(loadedItem.getDefinition().getWidth() == 1 && loadedItem.getDefinition().getLength() == 1) {
                this.room.getMappingComponent().getTile(loadedItem.getPosition()).addItem((IRoomFloorItem) loadedItem);
                return;
            }

            final List<Position> affectedPositions = Position.getAffectedPositions(
                    loadedItem.getDefinition().getLength(),
                    loadedItem.getDefinition().getWidth(),
                    loadedItem.getData().getRotation(),
                    loadedItem.getPosition()
            );

            for (final Position position : affectedPositions) {
                this.room.getMappingComponent().getTile(position).addItem((IRoomFloorItem) loadedItem);
            }

            ((IRoomFloorItem) loadedItem).setAffectedPositions(affectedPositions);
        }, this.room.getData().getId());
    }
}
