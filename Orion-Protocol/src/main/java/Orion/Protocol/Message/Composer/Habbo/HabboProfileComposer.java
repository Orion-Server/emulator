package Orion.Protocol.Message.Composer.Habbo;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Util.TimeUtil;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboProfileComposer extends MessageComposer {
    public HabboProfileComposer(final IHabbo viewerHabbo, final IHabbo targetHabbo) {
        super(ComposerHeaders.HabboProfileComposer);

        appendInt(targetHabbo.getData().getId());
        appendString(targetHabbo.getData().getUsername());
        appendString(targetHabbo.getData().getLook());
        appendString(targetHabbo.getData().getMotto());
        appendString(targetHabbo.getData().getAccountCreatedFormatted());

        appendInt(targetHabbo.getSettings().getAchievementScore());
        appendInt(targetHabbo.getMessenger().getFriends().size());
        appendBoolean(viewerHabbo.getMessenger().hasFriend(targetHabbo.getData().getId()));
        appendBoolean(false); // TODO: Friend requested
        appendBoolean(targetHabbo.getData().isOnline());

        appendInt(0); // TODO: Groups

        appendInt((int) (TimeUtil.now() - targetHabbo.getData().getLastOnline()));
        appendBoolean(true);
    }
}
