package Orion.Protocol;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.IServerMessageHandler;
import Orion.Api.Protocol.Message.Event.IMessageEventProvider;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParserProvider;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class ServerMessageHandler implements IServerMessageHandler {
    private final Logger logger = LogManager.getLogger();

    private final IMessageEventProvider messageEventProvider;

    private final IEventParserProvider eventParserProvider;

    @Inject
    public ServerMessageHandler(
            final IMessageEventProvider messageEventProvider,
            final IEventParserProvider eventParserProvider
    ) {
        this.messageEventProvider = messageEventProvider;
        this.eventParserProvider = eventParserProvider;
    }

    @Override
    public void handle(ISession session, IMessageEvent message) {
        final short headerId = message.getId();

        try {
            final IMessageEventHandler messageEventHandler = this.messageEventProvider.getMessageEventByHeaderId(headerId);

            if(messageEventHandler == null) {
                this.logger.warn(STR."[\{headerId}] Received message with unknown header.");
                return;
            }

            // TODO: Verify if the message is not a handshake event and the session is not authenticated

            final long startTime = System.currentTimeMillis();

            this.logger.debug(STR."Handling Event [\{message.getId()}] \{messageEventHandler.getClass().getSimpleName()}");

            if(messageEventHandler.getParser() != null) {
                try {
                    messageEventHandler.getParser().parse(message);
                } catch (Exception e) {
                    this.logger.error(STR."Error parsing message: \{messageEventHandler.getClass().getSimpleName()} with header: \{headerId}", e);
                    return;
                }
            }

            messageEventHandler.handle(session);

            final long endTime = System.currentTimeMillis();

            this.logMessageHandling(message, startTime, endTime);
        } catch (Exception e) {
            this.logger.error(STR."Error handling message: \{message} with header: \{headerId}", e);
        }
    }

    private void logMessageHandling(IMessageEvent message, long startTime, long endTime) {
        final long timeTaken = endTime - startTime;

        if(timeTaken >= 100) {
            this.logger.warn(STR."[\{message.getId()}] \{message.getClass().getSimpleName()} - Packet took \{endTime - startTime}ms.");
        }
    }
}
