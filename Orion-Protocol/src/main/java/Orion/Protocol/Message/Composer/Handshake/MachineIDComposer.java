package Orion.Protocol.Message.Composer.Handshake;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class MachineIDComposer extends Composer {
    private final String machineId;

    public MachineIDComposer(String machineId) {
        this.machineId = machineId;
    }

    @Override
    public short getId() {
        return ComposerHeaders.MachineIDComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendString(this.machineId);
    }
}
