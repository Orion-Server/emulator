package Orion.Protocol.Message.Composer.Habbo.Inventory;

import Orion.Api.Server.Game.Achievement.Data.IAchievement;
import Orion.Api.Server.Game.Achievement.Data.IAchievementLevel;
import Orion.Api.Server.Game.Achievement.IAchievementManager;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class InventoryAchievementsComposer extends MessageComposer {
    public InventoryAchievementsComposer(final IAchievementManager achievementManager) {
        super(ComposerHeaders.InventoryAchievementsComposer);

        appendInt(achievementManager.getAchievements().size());

        for (final IAchievement achievement : achievementManager.getAchievements().values()) {
           appendString(achievement.getName().replaceAll("^(ACH_)", ""));
           appendInt(achievement.getLevelsCount());

           for (final IAchievementLevel level : achievement.getLevels().values()) {
               appendInt(level.getLevel());
               appendInt(level.getProgressNeeded());
           }
        }
    }
}
