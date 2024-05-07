package Orion.Game.Room;

import Orion.Api.Server.Game.Navigator.Data.INavigatorPublicCategory;
import Orion.Api.Server.Game.Navigator.INavigatorManager;
import Orion.Api.Server.Game.Room.Data.IRoomCategory;
import Orion.Api.Server.Game.Room.Data.Model.IRoomModel;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.IRoomManager;
import Orion.Api.Server.Game.Room.Utils.RoomEnvironmentVariables;
import Orion.Api.Storage.Repository.Room.IRoomRepository;
import Orion.Game.Room.Data.Model.RoomModel;
import Orion.Game.Room.Data.RoomCategory;
import Orion.Game.Room.Factory.RoomFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

@Singleton
public class RoomManager implements IRoomManager {
    private final Logger logger = LogManager.getLogger();

    private final ConcurrentHashMap<Integer, IRoom> rooms;

    private final Map<Integer, IRoomCategory> roomCategories;

    private final Map<String, IRoomModel> roomModels;

    @Inject
    private IRoomRepository roomRepository;

    @Inject
    private RoomFactory roomFactory;

    @Inject
    private INavigatorManager navigatorManager;

    @Inject
    private RoomEnvironmentVariables roomEnvironmentVariables;

    public RoomManager() {
        this.rooms = new ConcurrentHashMap<>();
        this.roomModels = new ConcurrentHashMap<>();
        this.roomCategories = new ConcurrentHashMap<>();
    }

    @Override
    public void initialize() {
        this.loadPublicRooms();
        this.loadStaffPickedRooms();

        this.loadRoomCategories();
        this.loadRoomModels();

        this.roomEnvironmentVariables.initialize();
    }

    private void loadPublicRooms() {
        final AtomicInteger publicRoomsCount = new AtomicInteger(0);

        this.roomRepository.loadPublicRooms(result -> {
            if(result == null) return;

            try {
                final IRoom room = this.roomFactory.create(result);

                this.rooms.put(room.getData().getId(), room);
                publicRoomsCount.incrementAndGet();
            } catch (Exception e) {
                logger.error("Failed to load public room: {}", result.getInt("id"));
            }
        }, "1", "1");

        this.logger.debug("[{}] public rooms loaded successfully.", publicRoomsCount.get());
    }

    private void loadStaffPickedRooms() {
        final AtomicInteger pickedRoomsCount = new AtomicInteger(0);

        this.roomRepository.loadStaffPickedRooms(result -> {
            if(result == null) return;

            try {
                final INavigatorPublicCategory category = this.navigatorManager.getPublicCategoryById(
                        result.getInt("public_cat_id")
                );

                if(category == null) return;

                IRoom room = this.getRoomById(result.getInt("room_id"));

                if(room != null) {
                    category.addRoom(room);
                    return;
                }

                room = roomFactory.create(result);

                this.rooms.put(room.getData().getId(), room);
                pickedRoomsCount.incrementAndGet();
            } catch (Exception e) {
                logger.error("Failed to load staff picked room: {}", result.getInt("id"));
            }
        }, "1");

        this.logger.debug("[{}] staff picked rooms loaded successfully.", pickedRoomsCount.get());
    }

    private void loadRoomCategories() {
        this.roomRepository.loadFlatCategories(result -> {
            if(result == null) return;

            final IRoomCategory roomCategory = new RoomCategory(result);

            this.roomCategories.put(roomCategory.getId(), roomCategory);
        });

        this.logger.debug("[{}] room categories loaded successfully.", this.roomCategories.size());
    }

    private void loadRoomModels() {
        this.roomRepository.loadRoomModels(result -> {
            if(result == null) return;

            final IRoomModel roomModel = new RoomModel(result);

            this.roomModels.put(roomModel.getData().getName(), roomModel);
        });

        this.logger.debug("[{}] room models loaded successfully.", this.roomModels.size());
    }

    @Override
    public void addRoom(IRoom room) {
        this.rooms.putIfAbsent(room.getData().getId(), room);
    }

    @Override
    public IRoom getRoomById(int roomId) {
        return this.rooms.get(roomId);
    }

    @Override
    public ConcurrentHashMap<Integer, IRoom> getLoadedRooms() {
        return this.rooms;
    }

    @Override
    public Map<Integer, IRoomCategory> getRoomCategories() {
        return this.roomCategories;
    }

    @Override
    public IRoomCategory getCategoryFromTab(String tabName) {
        return this.roomCategories.values().stream()
                .filter(category -> category.getCaptionSave().equalsIgnoreCase(tabName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<IRoom> getLoadedRoomsBy(Predicate<IRoom> predicate) {
        final List<IRoom> result = new ArrayList<>();

        for (var room : this.rooms.values()) {
            if (predicate.test(room))
                result.add(room);
        }

        Collections.sort(result);
        return result;
    }
}
