package Orion.Protocol.Message.Composer.Alerts;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Object.Item.Enum.FurnitureMovementError;
import Orion.Api.Server.Game.Util.Alert.MiddleAlertType;
import Orion.Networking.Message.Composer;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class MiddleAlertComposer extends Composer {
    private final MiddleAlertType alertType;
    private final String error;

    public MiddleAlertComposer(final MiddleAlertType alertType, String error) {
        this.alertType = alertType;
        this.error = error;
    }

    public MiddleAlertComposer(final MiddleAlertType alertType, FurnitureMovementError error) {
        this.alertType = alertType;
        this.error = error.get();
    }

    @Override
    public short getId() {
        return ComposerHeaders.MiddleAlertComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendString(this.alertType.get());

        msg.appendInt(1);

        msg.appendString("message");
        msg.appendString(this.error);
    }
}
