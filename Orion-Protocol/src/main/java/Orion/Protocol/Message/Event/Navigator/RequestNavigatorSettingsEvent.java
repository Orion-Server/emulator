package Orion.Protocol.Message.Event.Navigator;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Protocol.Message.Composer.Navigator.NavigatorSettingsComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestNavigatorSettingsEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.RequestNavigatorSettingsEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        session.send(new NavigatorSettingsComposer(session.getHabbo().getNavigator().getNavigatorWindowSettings()));
    }
}
