package Orion.Protocol.Message.Event.Navigator;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.Navigator.INavigatorManager;
import Orion.Protocol.Message.Composer.Navigator.*;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestNavigatorDataEvent implements IMessageEventHandler {
    @Inject
    private INavigatorManager navigatorManager;

    @Override
    public int getId() {
        return EventHeaders.RequestNavigatorDataEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        session.send(
                new NavigatorSettingsComposer(session.getHabbo().getNavigator().getNavigatorWindowSettings()),
                new NavigatorMetaDataComposer(session.getHabbo().getNavigator()),
                new NavigatorLiftedRoomsComposer(),
                new NavigatorCollapsedCategoriesComposer(),
                new NavigatorEventCategoriesComposer(this.navigatorManager)
        );
    }
}
