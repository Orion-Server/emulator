package Orion.Api.Server.Game.Navigator.Tabs;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Navigator.Data.INavigatorFilterType;
import Orion.Api.Server.Game.Navigator.Data.INavigatorResultCategory;
import Orion.Api.Server.Game.Room.Data.IRoomCategory;

import java.util.List;

public interface INavigatorTab {
    List<INavigatorResultCategory> getResultForHabbo(IHabbo habbo);

    List<INavigatorResultCategory> getSearchedResultForHabbo(IHabbo habbo, INavigatorFilterType filterType, String search, IRoomCategory category);
}
