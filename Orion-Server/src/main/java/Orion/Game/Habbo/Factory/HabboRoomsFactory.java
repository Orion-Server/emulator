package Orion.Game.Habbo.Factory;

import Orion.Api.Server.Game.Habbo.Data.IHabboRooms;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.IRoomManager;
import Orion.Api.Server.Game.Utils.TimeUtil;
import Orion.Api.Storage.Repository.Habbo.IHabboRoomsRepository;
import Orion.Game.Habbo.Data.HabboRooms;
import Orion.Game.Room.Factory.RoomFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import gnu.trove.map.hash.THashMap;

@Singleton
public class HabboRoomsFactory {
    @Inject
    private IHabboRoomsRepository repository;

    @Inject
    private IRoomManager roomManager;

    @Inject
    private RoomFactory roomFactory;

    public IHabboRooms create(final int habboId) {
        final IHabboRooms habboRooms = new HabboRooms();

        habboRooms.setOwnRooms(this.loadRooms(habboId));
        habboRooms.setFavoriteRooms(this.loadFavoriteRooms(habboId));
        habboRooms.setRoomHistory(this.loadRoomHistory(habboId));
        habboRooms.setRoomsWithRights(this.loadRoomWithRights(habboId));

        return habboRooms;
    }

    private THashMap<Integer, IRoom> loadRooms(final int habboId) {
        final THashMap<Integer, IRoom> ownRooms = new THashMap<>();

        this.repository.loadRoomsForHabbo(result -> {
            if(result == null) return;

            IRoom room = this.roomManager.getRoomById(result.getInt("id"));

            if(room == null) {
                room = this.roomFactory.create(result);
            }

            ownRooms.putIfAbsent(room.getData().getId(), room);
        }, habboId);

        return ownRooms;
    }

    private THashMap<Integer, IRoom> loadFavoriteRooms(final int habboId) {
        final THashMap<Integer, IRoom> favoriteRooms = new THashMap<>();

        this.repository.loadFavoriteRoomsForHabbo(result -> {
            if(result == null) return;

            IRoom room = this.roomManager.getRoomById(result.getInt("id"));

            if(room == null) {
                room = roomFactory.create(result);
            }

            favoriteRooms.putIfAbsent(room.getData().getId(), room);
        }, habboId);

        return favoriteRooms;
    }

    private THashMap<Integer, IRoom> loadRoomHistory(final int habboId) {
        final THashMap<Integer, IRoom> roomHistory = new THashMap<>();

        this.repository.loadRoomHistoryForHabbo(result -> {
            if(result == null) return;

            IRoom room = this.roomManager.getRoomById(result.getInt("id"));

            if(room == null) {
                room = this.roomFactory.create(result);
            }

            roomHistory.putIfAbsent(room.getData().getId(), room);
        }, habboId, TimeUtil.now());

        return roomHistory;
    }

    private THashMap<Integer, IRoom> loadRoomWithRights(final int habboId) {
        final THashMap<Integer, IRoom> roomsWithRights = new THashMap<>();

        this.repository.loadRoomsWithRightsForHabbo(result -> {
            if(result == null) return;

            IRoom room = this.roomManager.getRoomById(result.getInt("id"));

            if(room == null) {
                room = this.roomFactory.create(result);
            }

            roomsWithRights.putIfAbsent(room.getData().getId(), room);
        }, habboId);

        return roomsWithRights;
    }
}
