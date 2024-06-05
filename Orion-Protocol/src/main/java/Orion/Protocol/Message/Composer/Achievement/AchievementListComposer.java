package Orion.Protocol.Message.Composer.Achievement;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Achievement.Data.IAchievement;
import Orion.Api.Server.Game.Achievement.IAchievementManager;
import Orion.Api.Server.Game.Habbo.Data.Achievement.IHabboAchievementProgress;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.Composer;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class AchievementListComposer extends Composer {
    private final IHabbo habbo;
    private final IAchievementManager achievementManager;

    public AchievementListComposer(final IHabbo habbo, final IAchievementManager achievementManager) {
        this.habbo = habbo;
        this.achievementManager = achievementManager;
    }

    @Override
    public short getId() {
        return ComposerHeaders.AchievementListComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.achievementManager.getAchievements().size());

        for (final IAchievement achievement : this.achievementManager.getAchievements().values()) {
            final IHabboAchievementProgress progress = this.habbo.getAchievements().getProgressByAchievement(achievement);

            msg.appendInt(achievement.getId());
            msg.appendInt(progress.getCurrentLevel());
            msg.appendString(achievement.getCompleteName(progress.getCurrentAchievementLevel(), progress.getNextAchievementLevel()));
            msg.appendInt(progress.getCurrentLevelProgress());
            msg.appendInt(progress.getNextLevelProgress());
            msg.appendInt(progress.getNextLevelRewardAmount());
            msg.appendInt(progress.getNextLevelRewardType());
            msg.appendInt(progress.getCurrentProgress());
            msg.appendBoolean(this.achievementManager.achievementCompleted(this.habbo, achievement));
            msg.appendString(achievement.getCategory().toString().toLowerCase());
            msg.appendString("");
            msg.appendInt(achievement.getLevelsCount());
            msg.appendInt(this.achievementManager.achievementCompleted(this.habbo, achievement) ? 1 : 0);
        }

        msg.appendString("");
    }
}