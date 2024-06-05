package Orion.Protocol.Message.Composer.Achievement;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.Composer;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class AchievementScoreComposer extends Composer {
    private final IHabbo habbo;

    public AchievementScoreComposer(final IHabbo habbo) {
        this.habbo = habbo;
    }

    @Override
    public short getId() {
        return ComposerHeaders.AchievementScoreComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.habbo.getSettings().getAchievementScore());
    }
}
