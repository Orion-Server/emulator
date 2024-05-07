package Orion.Writer.Habbo.Navigator;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.Data.Navigator.IHabboNavigatorSearch;

public abstract class HabboNavigatorSearchWriter {
    public static void write(
            IHabboNavigatorSearch search,
            IMessageComposer packet
    ) {
        packet.appendInt(search.getId());
        packet.appendString(search.getSearchCode());
        packet.appendString(search.getFilter());
        packet.appendString("");
    }
}

