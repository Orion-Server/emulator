package Orion.Game.Habbo.Data;

import Orion.Api.Server.Game.Habbo.Data.IHabboRooms;
import Orion.Api.Server.Game.Room.IRoom;
import gnu.trove.map.hash.THashMap;

public class HabboRooms implements IHabboRooms {
    private THashMap<Integer, IRoom> ownRooms;

    private THashMap<Integer, IRoom> roomHistory;

    private THashMap<Integer, IRoom> favoriteRooms;

    private THashMap<Integer, IRoom> roomsWithRights;

    public HabboRooms() {
        this.ownRooms = new THashMap<>();
        this.roomHistory = new THashMap<>();
        this.favoriteRooms = new THashMap<>();
        this.roomsWithRights = new THashMap<>();
    }

    @Override
    public void initialize() {
    }

    @Override
    public void setOwnRooms(THashMap<Integer, IRoom> ownRooms) {
        this.ownRooms.clear();
        this.ownRooms.putAll(ownRooms);
    }

    @Override
    public void setFavoriteRooms(THashMap<Integer, IRoom> favoriteRooms) {
        this.favoriteRooms.clear();
        this.favoriteRooms.putAll(favoriteRooms);
    }

    @Override
    public void setRoomHistory(THashMap<Integer, IRoom> roomHistory) {
        this.roomHistory.clear();
        this.roomHistory.putAll(roomHistory);
    }

    @Override
    public void setRoomsWithRights(THashMap<Integer, IRoom> roomsWithRights) {
        this.roomsWithRights.clear();
        this.roomsWithRights.putAll(roomsWithRights);
    }

    @Override
    public THashMap<Integer, IRoom> getOwnRooms() {
        return this.ownRooms;
    }

    @Override
    public THashMap<Integer, IRoom> getFavoriteRooms() {
        return this.favoriteRooms;
    }

    @Override
    public THashMap<Integer, IRoom> getRoomHistory() {
        return this.roomHistory;
    }

    @Override
    public THashMap<Integer, IRoom> getRoomsWithRights() {
        return this.roomsWithRights;
    }

    @Override
    public void dispose() {
        this.ownRooms.clear();
        this.roomHistory.clear();
        this.favoriteRooms.clear();
        this.roomsWithRights.clear();

        this.ownRooms = null;
        this.roomHistory = null;
        this.favoriteRooms = null;
        this.roomsWithRights = null;
    }
}
