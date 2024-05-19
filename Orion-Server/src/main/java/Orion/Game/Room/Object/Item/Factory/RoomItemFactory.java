package Orion.Game.Room.Object.Item.Factory;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Item.*;
import Orion.Api.Server.Game.Room.Object.Item.Base.IItemDefinition;
import Orion.Api.Server.Game.Room.Object.Item.Interaction.IRoomItemInteraction;
import Orion.Api.Storage.Result.IConnectionResult;
import Orion.Api.Util.Initializable;
import Orion.Game.Room.Object.Item.Composition.InteractionName;
import Orion.Game.Room.Object.Item.Interaction.RoomItemInteraction;
import Orion.Game.Room.Object.Item.RoomFloorItem;
import Orion.Game.Room.Object.Item.RoomWallItem;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import gnu.trove.map.hash.THashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;

import java.util.Set;

@Singleton
public class RoomItemFactory implements Initializable {
    private final Logger logger = LogManager.getLogger();

    @Inject
    private IRoomItemManager itemManager;

    private final THashMap<String, Class<? extends IRoomItemInteraction>> interactions;

    public RoomItemFactory() {
        this.interactions = new THashMap<>();
    }

    @Override
    public void initialize() {
        this.load();
    }

    private void load() {
        final Reflections reflections = new Reflections(RoomItemInteraction.class.getPackageName());
        final Set<Class<? extends RoomItemInteraction>> interactions = reflections.getSubTypesOf(RoomItemInteraction.class);

        for (final Class<? extends RoomItemInteraction> interaction : interactions) {
            if(!interaction.isAnnotationPresent(InteractionName.class)) {
                this.logger.error("Interaction {} does not have an InteractionName annotation", interaction.getName());
                continue;
            }

            this.interactions.putIfAbsent(interaction.getAnnotationsByType(InteractionName.class)[0].value(), interaction);
        }

        this.logger.debug("[{}] interactions loaded successfully.", this.interactions.size());
    }

    public IRoomItem create(
            int virtualId,
            final IConnectionResult data,
            final IRoom room
    ) {
        try {
            final IItemDefinition definition = this.itemManager.getItemDefinitionById(data.getInt("item_id"));

            if(definition == null) return null;

            return switch (definition.getType()) {
                case ItemDefinitionType.FLOOR -> this.createFloorItem(virtualId, data, room, definition);
                case ItemDefinitionType.WALL -> this.createWallItem(virtualId, data, room, definition);
                default -> null;
            };
        } catch (final Exception e) {
            System.out.println(STR."Failed to create a room item: \{e.getMessage()}");
        }

        return null;
    }

    public IRoomFloorItem createFloorItem(
            int virtualId,
            final IConnectionResult data,
            final IRoom room,
            final IItemDefinition definition
    ) {
        try {
            final IRoomItemInteraction interaction = this.interactions.get("default_floor").getConstructor().newInstance();

            return new RoomFloorItem(virtualId, room, data, interaction, definition);
        } catch (final Exception e) {
            this.logger.error("Failed to create floor item", e);
        }

        return null;
    }

    public IRoomWallItem createWallItem(
            int virtualId,
            final IConnectionResult data,
            final IRoom room,
            final IItemDefinition definition
    ) {
        try {
            final IRoomItemInteraction interaction = this.interactions.get("default_wall").getConstructor().newInstance();

            return new RoomWallItem(virtualId, room, data, interaction, definition);
        } catch (final Exception e) {
            this.logger.error("Failed to create wall item", e);
        }

        return null;
    }
}
