package Orion.Protocol.Message.Composer.Room.Rights;

import Orion.Api.Server.Game.Room.Enums.RoomRightLevel;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomRightsComposer extends MessageComposer {
    public RoomRightsComposer(final RoomRightLevel level) {
        super(ComposerHeaders.RoomRightsComposer);

        appendInt(level.ordinal());
    }
}
