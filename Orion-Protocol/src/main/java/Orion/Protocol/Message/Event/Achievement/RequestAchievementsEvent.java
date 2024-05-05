package Orion.Protocol.Message.Event.Achievement;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Achievement.IAchievementManager;
import Orion.Protocol.Message.Composer.Achievement.AchievementListComposer;
import Orion.Protocol.Message.Composer.Habbo.Inventory.InventoryAchievementsComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestAchievementsEvent implements IMessageEventHandler {
    @Inject
    private IAchievementManager achievementManager;

    @Override
    public int getId() {
        return EventHeaders.RequestAchievementsEvent;
    }

    @Override
    public IEventParser getParser() {
        return null;
    }

    public void handle(ISession session) {
        session.send(new AchievementListComposer(session.getHabbo(), this.achievementManager));
    }
}
