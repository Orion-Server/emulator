package Orion.Api.Server.Game.Navigator.Data;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayMode;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorLayoutDisplay;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorListAction;
import Orion.Api.Server.Game.Room.IRoom;

import java.util.List;

public interface INavigatorResultCategory extends Comparable<INavigatorResultCategory> {
    String getCategory();

    String getSearch();

    NavigatorListAction getAction();

    NavigatorLayoutDisplay getHidden();

    NavigatorDisplayMode getMode();

    boolean canShowInvisibleRooms();

    int getCategoryOrder();

    boolean filterRooms(INavigatorFilterType type, String search);

    List<IRoom> getRooms();

    void write(IMessageComposer packet);
}
