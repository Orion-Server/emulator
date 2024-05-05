package Orion.Protocol.Message.Composer.Achievement;

import Orion.Api.Server.Game.Achievement.Data.IAchievement;
import Orion.Api.Server.Game.Achievement.IAchievementManager;
import Orion.Api.Server.Game.Habbo.Data.Achievement.IHabboAchievementProgress;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class AchievementListComposer extends MessageComposer {
    public AchievementListComposer(
            final IHabbo habbo,
            final IAchievementManager achievementManager
    ) {
        super(ComposerHeaders.AchievementListComposer);

        appendInt(achievementManager.getAchievements().size());

        for (final IAchievement achievement : achievementManager.getAchievements().values()) {
            IHabboAchievementProgress progress = habbo.getAchievements().getProgressByAchievementName(achievement.getName());

            if(progress == null) {
                progress = achievementManager.getEmptyAchievementProgress();
            }

            appendInt(achievement.getId());
            appendInt(progress.getCurrentLevel());
            appendString(achievement.getCompleteName(progress.getCurrentAchievementLevel(), progress.getNextAchievementLevel()));
            appendInt(progress.getCurrentLevelProgress());
            appendInt(progress.getNextLevelProgress());
            appendInt(progress.getNextLevelRewardAmount());
            appendInt(progress.getNextLevelRewardType());
            appendInt(progress.getCurrentProgress());
            appendBoolean(achievementManager.achievementCompleted(habbo, achievement));
            appendString(achievement.getCategory().toString().toLowerCase());
            appendString("");
            appendInt(achievement.getLevelsCount());
            appendInt(achievementManager.achievementCompleted(habbo, achievement) ? 1 : 0);
        }

        appendString("");
    }
}