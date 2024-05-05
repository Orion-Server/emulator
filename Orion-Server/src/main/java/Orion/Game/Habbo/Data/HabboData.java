package Orion.Game.Habbo.Data;

import Orion.Api.Server.Game.Habbo.Data.IHabboData;
import Orion.Api.Storage.Result.IConnectionResult;

public class HabboData implements IHabboData {
    private int id;
    private String username;
    private String email;
    private String accountCreated;
    private String lastLogin;
    private String lastOnline;
    private String motto;
    private String look;
    private String gender;
    private int rank;
    private int credits;
    private int pixels;
    private int diamonds;
    private int seasonalPoints;
    private boolean isOnline;
    private String authTicket;
    private String registerIp;
    private String currentIp;
    private String machineId;
    private int homeRoom;

    public HabboData(IConnectionResult data) {
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
    public int getRank() {
        return this.rank;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getAccountCreated() {
        return this.accountCreated;
    }

    @Override
    public String getLastLogin() {
        return this.lastLogin;
    }

    @Override
    public String getLastOnline() {
        return this.lastOnline;
    }

    @Override
    public String getMotto() {
        return this.motto;
    }

    @Override
    public String getLook() {
        return this.look;
    }

    @Override
    public String getGender() {
        return this.gender;
    }

    @Override
    public int getCredits() {
        return this.credits;
    }

    @Override
    public int getPixels() {
        return this.pixels;
    }

    @Override
    public int getDiamonds() {
        return this.diamonds;
    }

    @Override
    public int getSeasonalPoints() {
        return this.seasonalPoints;
    }

    @Override
    public boolean isOnline() {
        return this.isOnline;
    }

    @Override
    public String getAuthTicket() {
        return this.authTicket;
    }

    @Override
    public String getRegisterIp() {
        return this.registerIp;
    }

    @Override
    public String getCurrentIp() {
        return this.currentIp;
    }

    @Override
    public String getMachineId() {
        return this.machineId;
    }

    @Override
    public int getHomeRoom() {
        return this.homeRoom;
    }

    @Override
    public void fill(IConnectionResult result) throws Exception {
        this.id = result.getInt("id");
        this.username = result.getString("username");
        this.email = result.getString("mail");
        this.accountCreated = result.getString("account_created");
        this.lastLogin = result.getString("last_login");
        this.lastOnline = result.getString("last_online");
        this.motto = result.getString("motto");
        this.look = result.getString("look");
        this.gender = result.getString("gender");
        this.rank = result.getInt("rank");
        this.credits = result.getInt("credits");
        this.isOnline = result.getBoolean("online");
        this.authTicket = result.getString("auth_ticket");
        this.registerIp = result.getString("ip_register");
        this.currentIp = result.getString("ip_current");
        this.machineId = result.getString("machine_id");
        this.homeRoom = result.getInt("home_room");
        this.pixels = 0;
        this.diamonds = 0;
        this.seasonalPoints = 0;
    }
}
