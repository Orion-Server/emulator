package Orion.Api.Server.Game.Habbo.Data.Inventory;

import Orion.Api.Server.Game.Room.Object.Item.Base.IItemDefinition;
import Orion.Api.Server.Game.Room.Object.Item.Data.ILimitedEditionData;
import Orion.Api.Util.IFillable;
import Orion.Api.Util.IWriteable;

public interface IHabboInventoryItem extends IFillable, IWriteable {
    long getId();

    int getUserId();

    IItemDefinition getItemDefinition();

    String getExtraData();

    ILimitedEditionData getLimitedEditionData();
}
