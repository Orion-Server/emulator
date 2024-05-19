package Orion.Api.Server.Game.Room.Object.Item;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Object.IRoomObject;
import Orion.Api.Server.Game.Room.Object.Item.Base.IItemDefinition;
import Orion.Api.Server.Game.Room.Object.Item.Data.IRoomItemData;
import Orion.Api.Server.Game.Room.Object.Item.Interaction.IRoomItemInteraction;

public interface IRoomItem extends IRoomObject {
    IRoomItemData getData();

    IItemDefinition getDefinition();

    void setInteraction(IRoomItemInteraction interaction);

    IRoomItemInteraction getInteraction();

    void write(final IMessageComposer composer);
}
