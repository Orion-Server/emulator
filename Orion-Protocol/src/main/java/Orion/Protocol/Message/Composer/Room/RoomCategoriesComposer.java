package Orion.Protocol.Message.Composer.Room;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomCategoriesComposer extends MessageComposer {
    private final String[] categories = new String[] {
            "${navigator.flatcategory.global.BC}",
            "${navigator.flatcategory.global.BUILDING}",
            "${navigator.flatcategory.global.CHAT}",
            "${navigator.flatcategory.global.FANSITE}",
            "${navigator.flatcategory.global.GAMES}",
            "${navigator.flatcategory.global.HELP}",
            "${navigator.flatcategory.global.LIFE}",
            "${navigator.flatcategory.global.OFFICIAL}",
            "${navigator.flatcategory.global.PARTY}"
    };

    public RoomCategoriesComposer() {
        super(ComposerHeaders.RoomCategoriesComposer);

        for (int i = 0; i <= this.categories.length - 1; i++) {
            appendInt(i + 1);
            appendString(this.categories[i]);
            appendBoolean(true);
            appendBoolean(false);
            appendString(this.categories[i]);
            appendString("");
            appendBoolean(false);
        }
    }
}
