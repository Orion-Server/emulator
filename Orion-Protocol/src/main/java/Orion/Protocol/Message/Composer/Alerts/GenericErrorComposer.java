package Orion.Protocol.Message.Composer.Alerts;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Util.Alert.GenericErrorType;
import Orion.Networking.Message.Composer;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class GenericErrorComposer extends Composer {
    private final GenericErrorType type;

    public GenericErrorComposer(GenericErrorType type) {
        this.type = type;
    }

    @Override
    public short getId() {
        return ComposerHeaders.GenericErrorComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.type.get());
    }
}
