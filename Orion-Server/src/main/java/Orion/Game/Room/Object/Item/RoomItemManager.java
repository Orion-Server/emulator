package Orion.Game.Room.Object.Item;

import Orion.Api.Server.Game.Room.Object.Item.Base.IItemDefinition;
import Orion.Api.Server.Game.Room.Object.Item.IRoomItemManager;
import Orion.Api.Storage.Repository.Room.IRoomItemsRepository;
import Orion.Game.Room.Object.Item.Base.ItemDefinition;
import Orion.Game.Room.Object.Item.Factory.RoomItemFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import gnu.trove.map.hash.THashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class RoomItemManager implements IRoomItemManager {
    private final Logger logger = LogManager.getLogger();

    private final THashMap<Integer, IItemDefinition> itemDefinitions;

    private final THashMap<Integer, Integer> spriteIdToDefinitionId;

    @Inject
    private IRoomItemsRepository repository;

    @Inject
    private RoomItemFactory itemFactory;

    public RoomItemManager() {
        this.itemDefinitions = new THashMap<>();
        this.spriteIdToDefinitionId = new THashMap<>();
    }

    @Override
    public void initialize() {
        this.loadItemDefinitions();

        this.itemFactory.initialize();
    }

    private void loadItemDefinitions() {
        this.repository.loadAllItemDefinitions(result -> {
            if(result == null) return;

            final IItemDefinition definition = new ItemDefinition(result);

            this.itemDefinitions.put(definition.getId(), definition);
            this.spriteIdToDefinitionId.put(definition.getSpriteId(), definition.getId());
        });

        this.logger.debug("[{}] item definitions loaded successfully.", this.itemDefinitions.size());
    }

    @Override
    public IItemDefinition getItemDefinition(int id) {
        return this.itemDefinitions.get(id);
    }

    @Override
    public IItemDefinition getItemDefinitionBySpriteId(int spriteId) {
        return this.itemDefinitions.get(this.spriteIdToDefinitionId.get(spriteId));
    }
}
