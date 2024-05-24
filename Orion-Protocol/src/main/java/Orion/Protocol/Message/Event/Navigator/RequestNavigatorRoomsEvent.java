package Orion.Protocol.Message.Event.Navigator;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.Navigator.Service.INavigatorSearchService;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestNavigatorRoomsEvent implements IMessageEventHandler {
    @Inject
    private INavigatorSearchService navigatorSearchService;

    @Override
    public int getId() {
        return EventHeaders.RequestNavigatorRoomsEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        final String tabName = this.normalizeTab(event.readString());
        final String searchQuery = event.readString();

        this.navigatorSearchService.commit(session.getHabbo(), tabName, searchQuery);
    }

    private String normalizeTab(String view) {
        if (view == null || view.equals("query") || view.equals("groups")) {
            return "hotel_view";
        }

        return view.trim();
    }
}
