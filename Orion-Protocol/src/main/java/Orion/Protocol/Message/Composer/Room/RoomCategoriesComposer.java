package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Server.Game.Room.Enums.RoomCategoryType;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomCategoriesComposer extends MessageComposer {
    public RoomCategoriesComposer() {
        super(ComposerHeaders.RoomCategoriesComposer);

        appendInt(RoomCategoryType.values().length);

        for(RoomCategoryType category : RoomCategoryType.values()) {
            appendInt(category.ordinal() + 1);
            appendString(category.get());
            appendBoolean(true);
            appendBoolean(false);
            appendString(category.get());
            appendString("");
            appendBoolean(false);
        }
    }
}
