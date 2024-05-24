package Orion.Protocol.Message.Event.Room.Chat;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Composer.Room.Chat.HabboTypingComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class HabboStopTypingEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.HabboStopTypingEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        if(!session.getHabbo().isInRoom()) return;

        session.getHabbo().getEntity().getRoom().broadcastMessage(
                new HabboTypingComposer(session.getHabbo().getEntity(), false)
        );
    }
}
