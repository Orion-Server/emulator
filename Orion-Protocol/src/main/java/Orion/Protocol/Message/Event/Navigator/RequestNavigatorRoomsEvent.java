package Orion.Protocol.Message.Event.Navigator;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Navigator.Service.INavigatorSearchService;
import Orion.Protocol.Message.Composer.Navigator.NavigatorSettingsComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Navigator.RequestNavigatorRoomsEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestNavigatorRoomsEvent implements IMessageEventHandler {
    @Inject
    private RequestNavigatorRoomsEventParser parser;

    @Inject
    private INavigatorSearchService navigatorSearchService;

    @Override
    public int getId() {
        return EventHeaders.RequestNavigatorRoomsEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    @Override
    public void handle(ISession session) {
        this.navigatorSearchService.commit(session.getHabbo(), this.parser.tabName, this.parser.searchQuery);
    }
}
