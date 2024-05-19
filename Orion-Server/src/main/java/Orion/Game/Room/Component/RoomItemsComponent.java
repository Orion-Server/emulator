package Orion.Game.Room.Component;

import Orion.Api.Server.Game.Room.Component.IRoomItemsComponent;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Api.Server.Game.Room.Object.Item.IRoomItem;
import Orion.Api.Server.Game.Room.Object.Item.ItemDefinitionType;
import Orion.Api.Storage.Repository.Room.IRoomItemsRepository;
import Orion.Game.Room.Object.Item.Factory.RoomItemFactory;
import com.google.inject.Inject;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomItemsComponent implements IRoomItemsComponent {
    private final IRoom room;

    @Inject
    private IRoomItemsRepository repository;

    @Inject
    private RoomItemFactory itemFactory;

    private final ConcurrentHashMap<Integer, IRoomFloorItem> floorItems;

    private final ConcurrentHashMap<Integer, IRoomItem> wallItems;

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
    public ConcurrentHashMap<Integer, IRoomItem> getWallItems() {
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

    private void loadRoomItems() {
        this.repository.loadItemsByRoomId(result -> {
            if(result == null) return;

            final IRoomItem loadedItem = this.itemFactory.create(this.virtualIdPointer.getAndIncrement(), result, this.room);

            if(loadedItem == null) return;

            if(loadedItem.getDefinition().getType() == ItemDefinitionType.FLOOR) {
                this.floorItems.put(loadedItem.getVirtualId(), (IRoomFloorItem) loadedItem);
            } else {
                this.wallItems.put(loadedItem.getVirtualId(), loadedItem);
            }

            if(loadedItem.getData().getOwnerId() != 0) {
                this.ownerNames.putIfAbsent(loadedItem.getData().getOwnerId(), "iNicollas");
            }
        }, this.room.getData().getId());
    }
}
