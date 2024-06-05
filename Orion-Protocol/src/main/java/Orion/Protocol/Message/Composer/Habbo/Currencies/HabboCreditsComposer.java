package Orion.Protocol.Message.Composer.Habbo.Currencies;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboCreditsComposer extends Composer {
    private final IHabbo habbo;

    public HabboCreditsComposer(final IHabbo habbo) {
        this.habbo = habbo;
    }

    @Override
    public short getId() {
        return ComposerHeaders.HabboCreditsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendString(STR."\{this.habbo.getData().getCredits()}.0");
    }
}
