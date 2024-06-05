package Orion.Protocol.Message.Composer.Habbo;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboDataComposer extends Composer {
    private final IHabbo habbo;

    public HabboDataComposer(final IHabbo habbo) {
        this.habbo = habbo;

    }

    @Override
    public short getId() {
        return ComposerHeaders.HabboDataComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.habbo.getData().getId());
        msg.appendString(this.habbo.getData().getUsername());
        msg.appendString(this.habbo.getData().getLook());
        msg.appendString(this.habbo.getData().getGender());
        msg.appendString(this.habbo.getData().getMotto());
        msg.appendString(this.habbo.getData().getUsername());
        msg.appendBoolean(false);
        msg.appendInt(this.habbo.getSettings().getRespectPointsReceived());
        msg.appendInt(this.habbo.getSettings().getRespectPointsGiven());
        msg.appendInt(this.habbo.getSettings().getPetRespectPointsToGive());
        msg.appendBoolean(false);
        msg.appendString("01-01-1970 00:00:00");
        msg.appendBoolean(this.habbo.getSettings().allowNameChange());
        msg.appendBoolean(false);
    }
}
