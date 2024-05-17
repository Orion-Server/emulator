package Orion.StressTest.Composer;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Event.EventHeaders;

public class RequestHeightmapComposer extends MessageComposer {
    public RequestHeightmapComposer() {
        super(EventHeaders.RequestHeightmapEvent);
    }
}
