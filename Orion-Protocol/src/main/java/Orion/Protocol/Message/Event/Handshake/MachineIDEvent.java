package Orion.Protocol.Message.Event.Handshake;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Annotations.HandshakeEvent;
import Orion.Protocol.Message.Composer.Handshake.MachineIDComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Hanshake.MachineIDEventParser;
import Orion.Protocol.Utils.HexUtils;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
@HandshakeEvent
public class MachineIDEvent implements IMessageEventHandler {
    private static final int hashLength = 64;

    @Inject
    private MachineIDEventParser parser;

    @Override
    public int getId() {
        return EventHeaders.MachineIDEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    public void handle(ISession session) {
        String machineId = this.parser.storedMachineId;

        if(machineId.startsWith("~") || machineId.length() != hashLength) {
            machineId = HexUtils.getRandom(hashLength);
            session.send(new MachineIDComposer(machineId));
        }

        session.setMachineId(machineId);
    }
}
