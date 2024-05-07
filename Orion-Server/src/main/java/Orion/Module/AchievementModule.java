package Orion.Module;

import Orion.Api.Server.Game.Achievement.IAchievementManager;
import Orion.Game.Achievement.AchievementManager;
import com.google.inject.AbstractModule;

public class AchievementModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IAchievementManager.class).to(AchievementManager.class);
    }
}
