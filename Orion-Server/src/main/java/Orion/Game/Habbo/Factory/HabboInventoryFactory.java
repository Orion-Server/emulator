package Orion.Game.Habbo.Factory;

import Orion.Api.Server.Game.Habbo.Data.IHabboInventory;
import Orion.Api.Server.Game.Habbo.Data.Inventory.Components.IInventoryItemsComponent;
import Orion.Api.Server.Game.Habbo.Data.Inventory.IHabboInventoryItem;
import Orion.Api.Server.Game.Room.Object.Item.Base.IItemDefinition;
import Orion.Api.Server.Game.Room.Object.Item.IRoomItemManager;
import Orion.Api.Storage.Repository.Habbo.IHabboInventoryRepository;
import Orion.Game.Habbo.Data.HabboInventory;
import Orion.Game.Habbo.Data.Inventory.Components.InventoryItemsComponent;
import Orion.Game.Habbo.Data.Inventory.HabboInventoryItem;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class HabboInventoryFactory {
    private final Logger logger = LogManager.getLogger();

    @Inject
    private IHabboInventoryRepository repository;

    @Inject
    private IRoomItemManager roomItemManager;

    public IHabboInventory create(int habboId) {
        final IInventoryItemsComponent itemsComponent = new InventoryItemsComponent(
            this.loadAllHabboInventory(habboId)
        );

        return new HabboInventory(itemsComponent);
    }

    private ConcurrentHashMap<Long, IHabboInventoryItem> loadAllHabboInventory(int habboId) {
        final ConcurrentHashMap<Long, IHabboInventoryItem> items = new ConcurrentHashMap<>();

        this.repository.loadAllHabboInventory(result -> {
            if(result == null) return;

            try {
                final IItemDefinition itemDefinition = this.roomItemManager.getItemDefinitionById(result.getInt("item_id"));

                if(itemDefinition == null) {
                    this.logger.warn(STR."Item definition not found for item id: \{result.getInt("item_id")} (habbo id: \{habboId})");
                    return;
                }

                final IHabboInventoryItem item = new HabboInventoryItem(result, itemDefinition);

                items.putIfAbsent(item.getId(), item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, habboId);

        return items;
    }
}
