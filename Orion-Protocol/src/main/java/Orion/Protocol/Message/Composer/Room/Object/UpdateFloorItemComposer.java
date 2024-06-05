package Orion.Protocol.Message.Composer.Room.Object;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class UpdateFloorItemComposer extends Composer {
    private final IRoomFloorItem item;

    public UpdateFloorItemComposer(final IRoomFloorItem item) {
        this.item = item;
    }

    @Override
    public short getId() {
        return ComposerHeaders.UpdateFloorItemComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        this.item.write(msg);
    }
}
