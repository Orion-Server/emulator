package Orion.Game.Habbo.Data.Messenger;

import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendRequest;
import Orion.Api.Storage.Result.IConnectionResult;

public class MessengerFriendRequest implements IMessengerFriendRequest {
    private int id;
    private String username;
    private String look;

    public MessengerFriendRequest(IConnectionResult data) {
        try {
            this.fill(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLook() {
        return this.look;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void fill(IConnectionResult data) throws Exception {
        this.id = data.getInt("id");
        this.username = data.getString("username");
        this.look = data.getString("look");
    }
}
