package Orion.Protocol.Message.Composer.Habbo;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Util.TimeUtil;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboProfileComposer extends Composer {
    private final IHabbo viewerHabbo;
    private final IHabbo targetHabbo;

    public HabboProfileComposer(final IHabbo viewerHabbo, final IHabbo targetHabbo) {
        this.viewerHabbo = viewerHabbo;
        this.targetHabbo = targetHabbo;
    }

    @Override
    public short getId() {
        return ComposerHeaders.HabboProfileComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.targetHabbo.getData().getId());
        msg.appendString(this.targetHabbo.getData().getUsername());
        msg.appendString(this.targetHabbo.getData().getLook());
        msg.appendString(this.targetHabbo.getData().getMotto());
        msg.appendString(this.targetHabbo.getData().getAccountCreatedFormatted());

        msg.appendInt(this.targetHabbo.getSettings().getAchievementScore());
        msg.appendInt(this.targetHabbo.getMessenger().getFriends().size());
        msg.appendBoolean(this.viewerHabbo.getMessenger().hasFriend(this.targetHabbo.getData().getId()));
        msg.appendBoolean(false); // TODO: Friend requested
        msg.appendBoolean(this.targetHabbo.getData().isOnline());

        msg.appendInt(0); // TODO: Groups

        msg.appendInt((int) (TimeUtil.now() - this.targetHabbo.getData().getLastOnline()));
        msg.appendBoolean(true);
    }
}
