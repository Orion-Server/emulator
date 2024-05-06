package Orion.Protocol.Message.Event.Habbo;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Composer.Habbo.HabboDataComposer;
import Orion.Protocol.Message.Composer.Habbo.HabboPerksComposer;
import Orion.Protocol.Message.Composer.Habbo.MeMenuSettingsComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

import java.util.ArrayList;

@Singleton
public class RequestHabboDataEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.RequestHabboDataEvent;
    }

    @Override
    public IEventParser getParser() {
        return null;
    }

    public void handle(ISession session) {
        final ArrayList<IMessageComposer> composers = new ArrayList<>();

        composers.add(new HabboDataComposer(session.getHabbo()));
        composers.add(new HabboPerksComposer(session.getHabbo()));
        composers.add(new MeMenuSettingsComposer(session.getHabbo()));

        session.send(composers);
    }
}
