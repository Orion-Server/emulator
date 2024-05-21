package Orion.Protocol.Message.Event.Room.Chat;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Util.Alert.MiddleAlertType;
import Orion.Protocol.Message.Composer.Alerts.MiddleAlertComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Room.Chat.HabboChatMessageEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class HabboChatMessageEvent implements IMessageEventHandler {
    @Inject
    private HabboChatMessageEventParser parser;

    @Override
    public int getId() {
        return EventHeaders.HabboChatMessageEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    @Override
    public void handle(ISession session) {
        if(!session.getHabbo().isInRoom()) return;

        // check if the user is muted

        if(this.parser.message.equalsIgnoreCase(":gc")) {
            System.gc();
            return;
        }

        if(this.parser.message.equalsIgnoreCase(":memory")) {
            final long allocatedMemory = (Runtime.getRuntime().totalMemory() / 1024) / 1024;

            session.send(new MiddleAlertComposer(MiddleAlertType.ADMIN_PERSISTENT, STR."""
                    ---------------
                    "Allocated Memory: \{allocatedMemory}MB"
                    Memory: \{(allocatedMemory - (Runtime.getRuntime().freeMemory() / 1024) / 1024)}MB
                    CPU Cores: \{Runtime.getRuntime().availableProcessors()} cores
                    """));
            return;
        }

        System.out.println(this.parser.message);
    }
}
