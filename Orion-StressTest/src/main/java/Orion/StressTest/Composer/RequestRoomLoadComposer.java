package Orion.StressTest.Composer;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Event.EventHeaders;

public class RequestRoomLoadComposer extends MessageComposer {
    public RequestRoomLoadComposer(int roomId, String password) {
        super(EventHeaders.RequestRoomLoadEvent);

        appendInt(roomId);
        appendString(password);
    }
}
