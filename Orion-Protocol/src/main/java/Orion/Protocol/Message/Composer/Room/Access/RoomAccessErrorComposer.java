package Orion.Protocol.Message.Composer.Room.Access;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Enums.RoomAccessError;
import Orion.Api.Server.Game.Room.Enums.RoomQueueAccessError;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomAccessErrorComposer extends Composer {
    private final int errorCode;
    private final String queueError;

    public RoomAccessErrorComposer(RoomAccessError error) {
        this.errorCode = error.get();
        this.queueError = "";
    }

    public RoomAccessErrorComposer(RoomAccessError error, RoomQueueAccessError queueError) {
        this.errorCode = error.get();
        this.queueError = queueError.get();
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomAccessErrorComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.errorCode);
        msg.appendString(this.queueError);
    }
}
