package Orion.Protocol.Message.Composer.Achievement;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class AchievementScoreComposer extends MessageComposer {
    public AchievementScoreComposer(final IHabbo habbo) {
        super(ComposerHeaders.AchievementScoreComposer);

        appendInt(habbo.getSettings().getAchievementScore());
    }
}
