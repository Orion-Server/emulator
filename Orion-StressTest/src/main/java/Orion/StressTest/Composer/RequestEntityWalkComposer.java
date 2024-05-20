package Orion.StressTest.Composer;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Event.EventHeaders;

public class RequestEntityWalkComposer extends MessageComposer {
    public RequestEntityWalkComposer(final int x, final int y) {
        super(EventHeaders.RequestEntityWalkEvent);

        appendInt(x);
        appendInt(y);
    }
}
