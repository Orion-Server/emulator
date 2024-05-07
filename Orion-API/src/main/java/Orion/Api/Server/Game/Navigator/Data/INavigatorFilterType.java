package Orion.Api.Server.Game.Navigator.Data;

import Orion.Api.Server.Game.Navigator.Enums.NavigatorFilterComparator;
import Orion.Api.Util.IFillable;

public interface INavigatorFilterType extends IFillable {
    String getKey();

    String getField();

    NavigatorFilterComparator getComparator();

    String getQuery();
}
