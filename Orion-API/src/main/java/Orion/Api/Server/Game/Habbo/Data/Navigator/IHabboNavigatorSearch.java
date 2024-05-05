package Orion.Api.Server.Game.Habbo.Data.Navigator;

import Orion.Api.Util.IFillable;

public interface IHabboNavigatorSearch extends IFillable {
    String getSearchCode();

    String getFilter();

    int getId();
}
