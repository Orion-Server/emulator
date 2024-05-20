package Orion.Api.Server.Game.Habbo.Data;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Util.IDisposable;
import Orion.Api.Util.Initializable;
import gnu.trove.map.hash.THashMap;

public interface IHabboRooms extends Initializable, IDisposable {
    void setOwnRooms(THashMap<Integer, IRoom> ownRooms);

    void setFavoriteRooms(THashMap<Integer, IRoom> favoriteRooms);

    void setRoomHistory(THashMap<Integer, IRoom> roomHistory);

    void setRoomsWithRights(THashMap<Integer, IRoom> roomsWithRights);

    THashMap<Integer, IRoom> getOwnRooms();

    void addOwnRoom(IRoom room);

    THashMap<Integer, IRoom> getFavoriteRooms();

    THashMap<Integer, IRoom> getRoomHistory();

    THashMap<Integer, IRoom> getRoomsWithRights();
}
