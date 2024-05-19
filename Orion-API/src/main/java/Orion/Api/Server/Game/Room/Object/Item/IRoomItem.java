package Orion.Api.Server.Game.Room.Object.Item;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Object.IRoomObject;
import Orion.Api.Server.Game.Room.Object.Item.Base.IItemDefinition;
import Orion.Api.Server.Game.Room.Object.Item.Data.IRoomItemData;

public interface IRoomItem extends IRoomObject {
    IRoomItemData getData();

    IItemDefinition getDefinition();

    void write(final IMessageComposer composer);
}
