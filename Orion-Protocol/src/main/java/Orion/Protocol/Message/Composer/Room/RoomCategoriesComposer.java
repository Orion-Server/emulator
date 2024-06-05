package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Enums.RoomCategoryType;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomCategoriesComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.RoomCategoriesComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(RoomCategoryType.values().length);

        for(final RoomCategoryType category : RoomCategoryType.values()) {
            msg.appendInt(category.ordinal() + 1);
            msg.appendString(category.get());
            msg.appendBoolean(true);
            msg.appendBoolean(false);
            msg.appendString(category.get());
            msg.appendString("");
            msg.appendBoolean(false);
        }
    }
}
