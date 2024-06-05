package Orion.Protocol.Message.Composer.Habbo.Inventory;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.Data.Inventory.Components.IInventoryBotsComponent;
import Orion.Api.Server.Game.Habbo.Data.Inventory.IHabboInventoryBot;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class InventoryBotsComposer extends Composer {
    private final IInventoryBotsComponent botsComponent;

    public InventoryBotsComposer(final IInventoryBotsComponent botsComponent) {
        this.botsComponent = botsComponent;
    }

    @Override
    public short getId() {
        return ComposerHeaders.InventoryBotsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(botsComponent.getBots().size());

        for (final IHabboInventoryBot bot : botsComponent.getBots().values()) {
            msg.appendInt(bot.getId());
            msg.appendString(bot.getName());
            msg.appendString(bot.getMotto());
            msg.appendString(STR."\{bot.getGender().toString().toLowerCase().charAt(0)}");
            msg.appendString(bot.getFigure());
        }
    }
}
