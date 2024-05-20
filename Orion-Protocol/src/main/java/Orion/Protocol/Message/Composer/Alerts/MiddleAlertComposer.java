package Orion.Protocol.Message.Composer.Alerts;

import Orion.Api.Server.Game.Room.Object.Item.Enum.FurnitureMovementError;
import Orion.Api.Server.Game.Util.Alert.MiddleAlertType;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class MiddleAlertComposer extends MessageComposer {
    public MiddleAlertComposer(final MiddleAlertType alertType, String error) {
        super(ComposerHeaders.MiddleAlertComposer);

        appendString(alertType.get());

        appendInt(1);

        appendString("message");
        appendString(error);
    }

    public MiddleAlertComposer(final MiddleAlertType alertType, FurnitureMovementError error) {
        super(ComposerHeaders.MiddleAlertComposer);

        appendString(alertType.get());

        appendInt(1);

        appendString("message");
        appendString(error.get());
    }
}
