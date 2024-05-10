package Orion.Api.Server.Game.Habbo;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Server.Game.Habbo.Compositions.IStatusable;
import Orion.Api.Server.Game.Habbo.Data.*;
import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;

public interface IHabbo extends IStatusable {
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

    IHabboMessenger getMessenger();

    void onDisconnect();

    IHabboEntity getEntity();

    void setEntity(IHabboEntity entity);
}
