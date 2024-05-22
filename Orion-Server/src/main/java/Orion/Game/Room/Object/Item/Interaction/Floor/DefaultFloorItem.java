package Orion.Game.Room.Object.Item.Interaction.Floor;

import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Game.Room.Object.Item.Composition.InteractionName;
import Orion.Game.Room.Object.Item.Interaction.RoomItemInteraction;

@InteractionName("default")
public class DefaultFloorItem extends RoomItemInteraction {
    private final IRoomFloorItem item;

    public DefaultFloorItem(final IRoomFloorItem item) {
        this.item = item;
    }
}
