package Orion.Api.Server.Game.Habbo;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Server.Game.Habbo.Data.*;

public interface IHabbo {
    void setSession(ISession session);

    ISession getSession();

    IHabboData getData();

    IHabboSettings getSettings();

    IHabboInventory getInventory();

    IHabboNavigator getNavigator();

    IHabboRooms getRooms();

    IHabboCurrencies getCurrencies();

    IHabboAchievements getAchievements();

    IHabboPermission getPermission();

    void onDisconnect();
}
