package Orion.Protocol.Message.Event.Handshake;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Protocol.Annotations.HandshakeEvent;
import Orion.Protocol.Message.Composer.Handshake.MachineIDComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Utils.HexUtils;
import com.google.inject.Singleton;

@Singleton
@HandshakeEvent
public class MachineIDEvent implements IMessageEventHandler {
    private static final int hashLength = 64;

    @Override
    public int getId() {
        return EventHeaders.MachineIDEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        String storedMachineId = event.readString();
        final String clientFingerprint = event.readString();
        final String capabilities = event.readString();

        if(storedMachineId.startsWith("~") || storedMachineId.length() != hashLength) {
            storedMachineId = HexUtils.getRandom(hashLength);
            session.send(new MachineIDComposer(storedMachineId));
        }

        session.setMachineId(storedMachineId);
    }
}
