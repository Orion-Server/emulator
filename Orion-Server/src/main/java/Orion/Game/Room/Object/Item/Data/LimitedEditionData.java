package Orion.Game.Room.Object.Item.Data;

import Orion.Api.Server.Game.Room.Object.Item.Data.ILimitedItemData;

public class LimitedEditionData implements ILimitedItemData {
    private final int limitedEditionNumber;

    private final int limitedEditionTotal;

    public LimitedEditionData(String data) {
        String[] split = data.split(":");

        this.limitedEditionNumber = Integer.parseInt(split[0]);
        this.limitedEditionTotal = Integer.parseInt(split[1]);
    }

    @Override
    public int getLimitedEditionNumber() {
        return this.limitedEditionNumber;
    }

    @Override
    public int getLimitedEditionTotal() {
        return this.limitedEditionTotal;
    }
}
