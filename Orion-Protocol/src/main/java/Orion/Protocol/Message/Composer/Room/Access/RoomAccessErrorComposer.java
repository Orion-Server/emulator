package Orion.Protocol.Message.Composer.Room.Access;

import Orion.Api.Server.Game.Room.Enums.RoomAccessError;
import Orion.Api.Server.Game.Room.Enums.RoomQueueAccessError;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomAccessErrorComposer extends MessageComposer {
    public RoomAccessErrorComposer(RoomAccessError error) {
        super(ComposerHeaders.RoomAccessErrorComposer);

        appendInt(error.get());
        appendString("");
    }

    public RoomAccessErrorComposer(RoomAccessError error, RoomQueueAccessError queueError) {
        super(ComposerHeaders.RoomAccessErrorComposer);

        appendInt(error.get());
        appendString(queueError.get());
    }
}
