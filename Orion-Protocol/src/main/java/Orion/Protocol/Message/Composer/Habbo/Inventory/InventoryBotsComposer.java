package Orion.Protocol.Message.Composer.Habbo.Inventory;

import Orion.Api.Server.Game.Habbo.Data.Inventory.Components.IInventoryBotsComponent;
import Orion.Api.Server.Game.Habbo.Data.Inventory.IHabboInventoryBot;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class InventoryBotsComposer extends MessageComposer {
    public InventoryBotsComposer(final IInventoryBotsComponent botsComponent) {
        super(ComposerHeaders.InventoryBotsComposer);

        appendInt(botsComponent.getBots().size());

        for (final IHabboInventoryBot bot : botsComponent.getBots().values()) {
            appendInt(bot.getId());
            appendString(bot.getName());
            appendString(bot.getMotto());
            appendString(STR."\{bot.getGender().toString().toLowerCase().charAt(0)}");
            appendString(bot.getFigure());
        }
    }
}
