package Orion.Writer.Habbo.Inventory;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.Data.Inventory.IHabboInventoryItem;
import Orion.Api.Server.Game.Room.Object.Item.ItemDefinitionType;

public abstract class HabboInventoryItemWriter {
    public static void write(
            final IHabboInventoryItem item,
            final IMessageComposer composer
    ) {
        composer.appendInt((int) item.getId());
        composer.appendString(item.getItemDefinition().getType().get().toUpperCase());
        composer.appendInt((int) item.getId());
        composer.appendInt(item.getItemDefinition().getSpriteId());

        if(item.getItemDefinition().isDecoration()) {
            switch (item.getItemDefinition().getItemName()) {
                case "landscape":
                    composer.appendInt(4);
                    break;
                case "floor":
                    composer.appendInt(3);
                    break;
                case "wallpaper":
                    composer.appendInt(2);
                    break;
                case "poster":
                    composer.appendInt(6);
                    break;
            }

            composer.appendInt(0);
            composer.appendString(item.getExtraData());
        } else {
            // TODO: Gnome box & Gifs
            composer.appendInt(1);

            composer.appendInt(item.getLimitedEditionData() != null ? 0x100 : 0);
            composer.appendString(item.getExtraData());

            if(item.getLimitedEditionData() != null) {
                composer.appendInt(item.getLimitedEditionData().getLimitedEditionNumber());
                composer.appendInt(item.getLimitedEditionData().getLimitedEditionTotal());
            }
        }

        composer.appendBoolean(item.getItemDefinition().isAllowRecycle());
        composer.appendBoolean(item.getItemDefinition().isAllowTrade());
        composer.appendBoolean(item.getLimitedEditionData() == null && item.getItemDefinition().isAllowInventoryStack());
        composer.appendBoolean(item.getItemDefinition().isAllowMarketplaceSell());

        composer.appendInt(-1);
        composer.appendBoolean(true);
        composer.appendInt(-1);

        if(item.getItemDefinition().getType().equals(ItemDefinitionType.FLOOR)) {
            composer.appendString("");
            composer.appendInt(1); // TODO: Gift
        }
    }
}
