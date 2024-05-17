package Orion.StressTest.Composer;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Event.EventHeaders;

public class SSOTicketComposer extends MessageComposer {
    public SSOTicketComposer(String ssoTicket) {
        super(EventHeaders.SSOTicketEvent);

        appendString(ssoTicket);
    }
}
