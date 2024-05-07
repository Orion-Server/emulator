package Orion.Game.HotelView.Data;

import Orion.Api.Server.Game.HotelView.Data.IHallOfFameWinner;
import Orion.Api.Storage.Result.IConnectionResult;

public class HallOfFameWinner implements IHallOfFameWinner {
    private int id;
    private String username;
    private String look;
    private int points;

    public HallOfFameWinner(IConnectionResult data) {
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
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getLook() {
        return this.look;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public void fill(IConnectionResult data) throws Exception {
        this.id = data.getInt("id");
        this.username = data.getString("username");
        this.look = data.getString("look");
        this.points = data.getInt("hof_points");
    }

    @Override
    public int compareTo(IHallOfFameWinner o) {
        return o.getPoints() - this.getPoints();
    }
}
