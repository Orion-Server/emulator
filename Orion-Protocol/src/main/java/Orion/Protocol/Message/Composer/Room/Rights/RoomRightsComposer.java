package Orion.Protocol.Message.Composer.Room.Rights;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Enums.RoomRightLevel;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomRightsComposer extends Composer {
    private final RoomRightLevel level;

    public RoomRightsComposer(final RoomRightLevel level) {
        this.level = level;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomRightsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(level.ordinal());
    }
}
