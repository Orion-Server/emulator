package Orion.Protocol.Message.Composer.Room.Object;

import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class UpdateFloorItemComposer extends MessageComposer {
    public UpdateFloorItemComposer(final IRoomFloorItem item) {
        super(ComposerHeaders.UpdateFloorItemComposer);

        item.write(this);
    }
}
