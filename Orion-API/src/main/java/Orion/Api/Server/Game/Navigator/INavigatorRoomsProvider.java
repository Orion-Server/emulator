package Orion.Api.Server.Game.Navigator;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.Data.IRoomCategory;
import Orion.Api.Server.Game.Room.IRoom;
import gnu.trove.map.hash.THashMap;

import java.util.List;

public interface INavigatorRoomsProvider {
    List<IRoom> getRoomsForCategory(String category, IHabbo habbo);

    List<IRoom> getPublicRooms();

    List<IRoom> getPopularRooms();

    List<IRoom> getPromotedRooms();

    List<IRoom> getTopRatedRooms();

    THashMap<IRoomCategory, List<IRoom>> getRoomsFromCategories(IHabbo habbo);
}
