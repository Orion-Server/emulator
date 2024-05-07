package Orion.Game.Navigator.Data;

import Orion.Api.Server.Game.Navigator.Data.INavigatorPublicCategory;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayMode;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Storage.Result.IConnectionResult;

import java.util.ArrayList;
import java.util.List;

public class NavigatorPublicCategory implements INavigatorPublicCategory {
    private int id;
    private String name;
    private final List<IRoom> rooms;
    private NavigatorDisplayMode displayMode;
    private int order;

    public NavigatorPublicCategory(IConnectionResult data) {
        this.rooms = new ArrayList<>();

        try {
            this.fill(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<IRoom> getRooms() {
        return this.rooms;
    }

    public void addRoom(IRoom room) {
        this.rooms.add(room);
    }

    public void removeRoom(IRoom room) {
        this.rooms.remove(room);
    }

    @Override
    public NavigatorDisplayMode getDisplayMode() {
        return this.displayMode;
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    public void fill(IConnectionResult result) throws Exception {
        this.id = result.getInt("id");
        this.name = result.getString("name");
        this.displayMode = NavigatorDisplayMode.fromType(Integer.parseInt(result.getString("image")));
        this.order = result.getInt("order_num");
    }
}