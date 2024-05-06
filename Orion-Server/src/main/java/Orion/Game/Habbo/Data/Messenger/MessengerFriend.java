package Orion.Game.Habbo.Data.Messenger;

import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriend;
import Orion.Api.Server.Game.Habbo.Enums.HabboGender;
import Orion.Api.Storage.Result.IConnectionResult;

public class MessengerFriend implements IMessengerFriend {
    private int id;
    private String username;
    private HabboGender gender;
    private int online = 0;
    private String look = "";
    private String motto = "";
    private short relationType;
    private int categoryId = 0;
    private boolean inRoom;
    private int userOne = 0;

    public MessengerFriend(IConnectionResult data) {
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
    public HabboGender getGender() {
        return this.gender;
    }

    @Override
    public int getOnline() {
        return this.online;
    }

    @Override
    public String getLook() {
        return this.look;
    }

    @Override
    public String getLookIfAvailable() {
        return this.online == 1 ? this.look : "";
    }

    @Override
    public String getMotto() {
        return this.motto;
    }

    @Override
    public short getRelationType() {
        return this.relationType;
    }

    @Override
    public int getCategoryId() {
        return this.categoryId;
    }

    @Override
    public int getUserOne() {
        return this.userOne;
    }

    @Override
    public boolean isInRoom() {
        return this.inRoom;
    }

    @Override
    public void fill(IConnectionResult data) throws Exception {
        this.id = data.getInt("id");
        this.username = data.getString("username");
        this.gender = HabboGender.valueOf(data.getString("gender"));
        this.online = data.getInt("online");
        this.motto = data.getString("motto");
        this.look = data.getString("look");
        this.relationType = (short) data.getInt("relation");
        this.categoryId = data.getInt("category");
        this.userOne = data.getInt("user_one_id");
        this.inRoom = false;
    }
}
