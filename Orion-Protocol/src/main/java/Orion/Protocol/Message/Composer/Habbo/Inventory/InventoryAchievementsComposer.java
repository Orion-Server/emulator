package Orion.Protocol.Message.Composer.Habbo.Inventory;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Achievement.Data.IAchievement;
import Orion.Api.Server.Game.Achievement.Data.IAchievementLevel;
import Orion.Api.Server.Game.Achievement.IAchievementManager;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class InventoryAchievementsComposer extends Composer {
    private final IAchievementManager achievementManager;

    public InventoryAchievementsComposer(final IAchievementManager achievementManager) {
        this.achievementManager = achievementManager;
    }

    @Override
    public short getId() {
        return ComposerHeaders.InventoryAchievementsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.achievementManager.getAchievements().size());

        for (final IAchievement achievement : this.achievementManager.getAchievements().values()) {
            msg.appendString(achievement.getName().replaceAll("^(ACH_)", ""));
            msg.appendInt(achievement.getLevelsCount());

            for (final IAchievementLevel level : achievement.getLevels().values()) {
                msg.appendInt(level.getLevel());
                msg.appendInt(level.getProgressNeeded());
            }
        }
    }
}
