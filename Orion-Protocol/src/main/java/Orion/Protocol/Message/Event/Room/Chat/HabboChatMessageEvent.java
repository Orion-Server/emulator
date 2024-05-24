package Orion.Protocol.Message.Event.Room.Chat;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Boot.Utils.IEmulatorRuntimeVariables;
import Orion.Api.Server.Game.Util.Alert.MiddleAlertType;
import Orion.Protocol.Message.Composer.Alerts.MiddleAlertComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class HabboChatMessageEvent implements IMessageEventHandler {
    @Inject
    private IEmulatorRuntimeVariables runtimeVariables;

    @Override
    public int getId() {
        return EventHeaders.HabboChatMessageEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        final String message = event.readString();
        final int bubbleId = event.readInt();

        if(!session.getHabbo().isInRoom()) return;

        // check if the user is muted

        if(message.equalsIgnoreCase(":gc")) {
            System.gc();
            return;
        }

        if(message.equalsIgnoreCase(":memory")) {
            final long allocatedMemory = (Runtime.getRuntime().totalMemory() / 1024) / 1024;

            session.send(new MiddleAlertComposer(MiddleAlertType.ADMIN_PERSISTENT, STR."""
                    "Allocated Memory: \{allocatedMemory}MB"
                    Memory: \{(allocatedMemory - (Runtime.getRuntime().freeMemory() / 1024) / 1024)}MB
                    CPU Cores: \{Runtime.getRuntime().availableProcessors()} cores
                    Onlines: \{this.runtimeVariables.getPlayersOnline()}
                    """));
        }
    }
}
