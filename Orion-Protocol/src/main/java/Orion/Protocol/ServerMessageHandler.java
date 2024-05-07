package Orion.Protocol;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Networking.Session.ISessionManager;
import Orion.Api.Protocol.IServerMessageHandler;
import Orion.Api.Protocol.Message.Event.IMessageEventProvider;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Protocol.Annotations.HandshakeEvent;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class ServerMessageHandler implements IServerMessageHandler {
    private final Logger logger = LogManager.getLogger();

    private final IMessageEventProvider messageEventProvider;

    private final ISessionManager sessionManager;

    @Inject
    public ServerMessageHandler(
            final IMessageEventProvider messageEventProvider,
            final ISessionManager sessionManager
    ) {
        this.messageEventProvider = messageEventProvider;
        this.sessionManager = sessionManager;
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

            if(!session.isAuthenticated() && !messageEventHandler.getClass().isAnnotationPresent(HandshakeEvent.class)) {
                this.logger.warn(STR."[\{headerId}] Received handshake event without being authenticated.");
                this.sessionManager.disposeSession(session);
                return;
            }

            final long startTime = System.currentTimeMillis();

            this.logger.debug(STR.">> Handling Event [\{message.getId()}] \{messageEventHandler.getClass().getSimpleName()}");

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
