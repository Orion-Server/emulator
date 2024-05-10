package Orion.Protocol.Message.Composer.Alerts;

import Orion.Api.Server.Game.Util.Alert.GenericErrorType;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class GenericErrorComposer extends MessageComposer {
    public GenericErrorComposer(GenericErrorType type) {
        super(ComposerHeaders.GenericErrorComposer);

        appendInt(type.get());
    }
}
