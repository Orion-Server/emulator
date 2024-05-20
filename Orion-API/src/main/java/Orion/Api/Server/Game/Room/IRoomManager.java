package Orion.Api.Server.Game.Room;

import Orion.Api.Server.Game.Room.Data.IRoomCategory;
import Orion.Api.Server.Game.Room.Data.Model.IRoomModel;
import Orion.Api.Util.Initializable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

public interface IRoomManager extends Initializable {
    IRoom getRoomById(int roomId);

    void addRoom(IRoom room);

    IRoomModel getRoomModelByName(String modelName);

    ConcurrentHashMap<Integer, IRoom> getLoadedRooms();

    Map<Integer, IRoomCategory> getRoomCategories();

    IRoomCategory getCategoryFromTab(String tabName);

    boolean roomCategoryExists(int categoryId);

    List<IRoom> getLoadedRoomsBy(Predicate<IRoom> predicate);

    boolean roomModelExists(String modelName);
}
