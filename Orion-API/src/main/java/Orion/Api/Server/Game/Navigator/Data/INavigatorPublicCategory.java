package Orion.Api.Server.Game.Navigator.Data;

import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayMode;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Util.IFillable;

import java.util.List;

public interface INavigatorPublicCategory extends IFillable {
    int getId();

    String getName();

    List<IRoom> getRooms();

    int getOrder();

    NavigatorDisplayMode getDisplayMode();

    void addRoom(IRoom room);

    void removeRoom(IRoom room);
}
