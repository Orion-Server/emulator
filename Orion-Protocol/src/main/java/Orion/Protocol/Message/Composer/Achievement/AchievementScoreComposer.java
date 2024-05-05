package Orion.Protocol.Message.Composer.Achievement;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class AchievementScoreComposer extends MessageComposer {
    public AchievementScoreComposer() {
        super(ComposerHeaders.AchievementScoreComposer);

        appendInt(100);
    }
}
