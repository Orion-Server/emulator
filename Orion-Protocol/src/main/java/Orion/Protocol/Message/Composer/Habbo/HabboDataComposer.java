package Orion.Protocol.Message.Composer.Habbo;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboDataComposer extends MessageComposer {
    public HabboDataComposer(final IHabbo habbo) {
        super(ComposerHeaders.HabboDataComposer);

        appendInt(habbo.getData().getId());
        appendString(habbo.getData().getUsername());
        appendString(habbo.getData().getLook());
        appendString(habbo.getData().getGender());
        appendString(habbo.getData().getMotto());
        appendString(habbo.getData().getUsername());
        appendBoolean(false);
        appendInt(habbo.getSettings().getRespectPointsReceived());
        appendInt(habbo.getSettings().getRespectPointsGiven());
        appendInt(habbo.getSettings().getPetRespectPointsToGive());
        appendBoolean(false);
        appendString("01-01-1970 00:00:00");
        appendBoolean(habbo.getSettings().allowNameChange());
        appendBoolean(false);
    }
}
