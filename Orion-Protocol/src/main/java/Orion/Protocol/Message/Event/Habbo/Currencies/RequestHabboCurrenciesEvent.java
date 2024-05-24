package Orion.Protocol.Message.Event.Habbo.Currencies;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Protocol.Message.Composer.Habbo.Currencies.HabboCreditsComposer;
import Orion.Protocol.Message.Composer.Habbo.Currencies.HabboCurrenciesComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestHabboCurrenciesEvent implements IMessageEventHandler {
    @Inject
    private IEmulatorDatabaseSettings databaseSettings;

    @Override
    public int getId() {
        return EventHeaders.RequestHabboCurrenciesEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        session.send(new HabboCreditsComposer(session.getHabbo()));
        session.send(new HabboCurrenciesComposer(session.getHabbo(),
                this.databaseSettings.getSettingOrDefault("seasonal.types", "")
        ));
    }
}
