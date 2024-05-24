package Orion.Protocol.Message.Event.Habbo;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Protocol.Message.Composer.Habbo.HabboDataComposer;
import Orion.Protocol.Message.Composer.Habbo.HabboPerksComposer;
import Orion.Protocol.Message.Composer.Habbo.MeMenuSettingsComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestHabboDataEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.RequestHabboDataEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        session.send(
                new HabboDataComposer(session.getHabbo()),
                new HabboPerksComposer(session.getHabbo()),
                new MeMenuSettingsComposer(session.getHabbo())
        );
    }
}
