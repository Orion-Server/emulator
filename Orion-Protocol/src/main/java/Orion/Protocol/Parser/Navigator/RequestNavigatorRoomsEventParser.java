package Orion.Protocol.Parser.Navigator;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestNavigatorRoomsEventParser implements IEventParser {
    public String tabName;
    public String searchQuery;

    @Override
    public int getId() {
        return EventHeaders.RequestNavigatorRoomsEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.tabName = this.normalizeTab(messageEvent.readString());
        this.searchQuery = messageEvent.readString();
    }

    private String normalizeTab(String view) {
        if (view == null || view.equals("query") || view.equals("groups")) {
            return "hotel_view";
        }

        return view.trim();
    }
}
