package Orion.Api.Server.Game.Navigator.Service;

import Orion.Api.Server.Game.Habbo.IHabbo;

public interface INavigatorSearchService {
    void commit(IHabbo habbo, String tabName, String query);
}
