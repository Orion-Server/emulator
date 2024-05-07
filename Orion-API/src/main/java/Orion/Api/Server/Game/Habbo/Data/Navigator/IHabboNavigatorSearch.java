package Orion.Api.Server.Game.Habbo.Data.Navigator;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Util.IFillable;
import Orion.Api.Util.IWriteable;

public interface IHabboNavigatorSearch extends IFillable, IWriteable {
    String getSearchCode();

    String getFilter();

    int getId();

    void write(IMessageComposer packet);
}
