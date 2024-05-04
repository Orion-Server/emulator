package Orion.Protocol.Parser.Hanshake;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class MachineIDEventParser implements IEventParser {
    public String storedMachineId;
    public String clientFingerprint;
    public String capabilities;

    @Override
    public int getId() {
        return EventHeaders.MachineIDEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.storedMachineId = messageEvent.readString();
        this.clientFingerprint = messageEvent.readString();
        this.capabilities = messageEvent.readString();
    }
}
