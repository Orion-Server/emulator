package Orion.StressTest.Composer;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Event.EventHeaders;

public class ClientHelloComposer extends MessageComposer {
    public ClientHelloComposer() {
        super(EventHeaders.ClientHelloEvent);

        appendString("TEST-0.1");
        appendString("1");
        appendInt(1);
        appendInt(0);
    }
}
