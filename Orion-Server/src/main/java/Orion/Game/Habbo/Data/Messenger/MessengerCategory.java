package Orion.Game.Habbo.Data.Messenger;

import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerCategory;
import Orion.Api.Storage.Result.IConnectionResult;

public class MessengerCategory implements IMessengerCategory {
    private int id;
    private int userId;
    private String name;

    public MessengerCategory(IConnectionResult data) {
        try {
            this.fill(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getUserId() {
        return this.userId;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void fill(IConnectionResult data) throws Exception {
        this.id = data.getInt("id");
        this.userId = data.getInt("user_id");
        this.name = data.getString("name");
    }
}
