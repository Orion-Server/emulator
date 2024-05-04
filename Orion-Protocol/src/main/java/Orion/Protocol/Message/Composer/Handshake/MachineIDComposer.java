package Orion.Protocol.Message.Composer.Handshake;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class MachineIDComposer extends MessageComposer {
    public MachineIDComposer(String machineId) {
        super(ComposerHeaders.MachineIDComposer);

        appendString(machineId);
    }
}
