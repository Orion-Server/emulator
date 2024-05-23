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
import Orion.Protocol.Message.Composer.Room.Object.UpdateFloorItemComposer;
import Orion.Protocol.Message.Composer.Room.UpdateTileStackHeightComposer;
import com.google.inject.Inject;

import java.util.ArrayList;
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
    public IRoomFloorItem getFloorItemByVirtualId(int virtualId) {
        return this.floorItems.get(virtualId);
    }

    @Override
    public IRoomWallItem getWallItemByVirtualId(int virtualId) {
        return this.wallItems.get(virtualId);
    }

    @Override
    public ConcurrentHashMap<Integer, String> getOwnerNames() {
        return this.ownerNames;
    }

    @Override
    public FurnitureMovementError applyItemMovement(final IRoomFloorItem item, Position position, int rotation) {
        final IRoomTile targetTile = this.room.getMappingComponent().getTile(position);

        if(targetTile == null) {
            return FurnitureMovementError.INVALID_MOVE;
        }

        final List<Position> newAffectedPositions = new ArrayList<>() {
            { add(targetTile.getPosition()); }
        };

        if(item.getDefinition().getLength() > 1 || item.getDefinition().getWidth() > 1) {
            newAffectedPositions.clear();
            newAffectedPositions.addAll(Position.getAffectedPositions(
                    item.getDefinition().getLength(), item.getDefinition().getWidth(), rotation, targetTile.getPosition()
            ));
        }

        final FurnitureMovementError error = this.checkItemMovementError(item, targetTile, rotation, newAffectedPositions);

        if(!error.equals(FurnitureMovementError.NONE)) {
            return error;
        }

        this.moveItemTo(item, targetTile, rotation, newAffectedPositions);

        return FurnitureMovementError.NONE;
    }

    private FurnitureMovementError checkItemMovementError(final IRoomFloorItem item, IRoomTile targetTile, int rotation, final List<Position> newAffectedPositions) {
        if(this.floorItems.size() + this.wallItems.size() >= 2500) { // TODO: Fetch this from configuration
            return FurnitureMovementError.MAX_ITEMS;
        }

        if(targetTile.getPosition().equals(item.getPosition()) && rotation == item.getData().getRotation()) {
            System.out.println("position and rotation are the same");
            return FurnitureMovementError.NONE;
        }

        for (final Position affectedPosition : newAffectedPositions) {
            if(affectedPosition.getX() < 0 || affectedPosition.getY() < 0 || affectedPosition.getX() >= this.room.getModel().getMapSizeX() || affectedPosition.getY() >= this.room.getModel().getMapSizeY()) {
                System.out.println("Invalid position in the map size");
                return FurnitureMovementError.INVALID_MOVE;
            }

            final IRoomTile affectedTile = this.room.getMappingComponent().getTile(affectedPosition);

            if(affectedTile == null || affectedPosition.equals(this.room.getMappingComponent().getDoorTile().getPosition())) {
                System.out.println("tile not found or position equals door tile");
                return FurnitureMovementError.INVALID_MOVE;
            }

            if(affectedTile.getTopItem() != null && affectedTile.getTopItem().getData().getId() == item.getData().getId()) continue;

            if(!affectedTile.canStack()) {
                System.out.println("tile cannot stack");

                return FurnitureMovementError.CANT_STACK;
            }

            if(!affectedTile.getEntities().isEmpty()) {
                System.out.println("tile has habbos");
                return FurnitureMovementError.TILE_HAS_HABBOS;
            }

            if(affectedTile.getTopItem() != null && !affectedTile.getTopItem().getDefinition().isAllowStack()) {
                System.out.println("top item cannot stack");
                return FurnitureMovementError.CANT_STACK;
            }
        }

        return FurnitureMovementError.NONE;
    }

    private void moveItemTo(final IRoomFloorItem item, IRoomTile targetTile, int rotation, final List<Position> newAffectedPositions) {
        final List<IRoomTile> tilesToUpdate = new ArrayList<>();
        for (final Position affectedPosition : item.getAffectedPositions()) {
            final IRoomTile tile = this.room.getMappingComponent().getTile(affectedPosition);

            tile.removeItem(item);
            tilesToUpdate.add(tile);
        }

        item.getData().setPosition(targetTile.getPosition());
        item.getData().setRotation(rotation);
        item.setAffectedPositions(newAffectedPositions);

        for (final Position affectedPosition : newAffectedPositions) {
            final IRoomTile tile = this.room.getMappingComponent().getTile(affectedPosition);

            tile.addItem(item);
            tilesToUpdate.add(tile);
        }

        if(!tilesToUpdate.isEmpty()) {
            this.room.broadcastMessage(new UpdateTileStackHeightComposer(tilesToUpdate));
        }

        this.room.broadcastMessage(new UpdateFloorItemComposer(item));
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
