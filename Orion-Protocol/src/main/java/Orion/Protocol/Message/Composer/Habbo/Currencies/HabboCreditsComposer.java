package Orion.Protocol.Message.Composer.Habbo.Currencies;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboCreditsComposer extends MessageComposer {
    public HabboCreditsComposer(final IHabbo habbo) {
        super(ComposerHeaders.HabboCreditsComposer);

        appendString(STR."\{habbo.getData().getCredits()}.0");
    }
}
