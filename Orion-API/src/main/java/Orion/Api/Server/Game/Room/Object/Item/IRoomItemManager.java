package Orion.Api.Server.Game.Room.Object.Item;

import Orion.Api.Server.Game.Room.Object.Item.Base.IItemDefinition;
import Orion.Api.Util.Initializable;

public interface IRoomItemManager extends Initializable {
    IItemDefinition getItemDefinitionById(int id);

    IItemDefinition getItemDefinitionBySpriteId(int spriteId);
}
