package Orion.Game.Room.Data.Model;

import Orion.Api.Server.Game.Room.Data.Model.IRoomModel;
import Orion.Api.Server.Game.Room.Data.Model.IRoomModelData;
import Orion.Api.Storage.Result.IConnectionResult;

public class RoomModel implements IRoomModel {
    private final IRoomModelData data;

    public RoomModel(IConnectionResult result) {
        this.data = new RoomModelData(result);

        try {
            this.calculateModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateModel() {
        // TODO: Implement this method
    }

    public IRoomModelData getData() {
        return this.data;
    }
}
