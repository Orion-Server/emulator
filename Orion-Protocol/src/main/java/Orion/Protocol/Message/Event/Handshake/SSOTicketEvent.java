package Orion.Protocol.Message.Event.Handshake;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.Habbo.Provider.IHabboLoginProvider;
import Orion.Api.Server.Task.IThreadManager;
import Orion.Protocol.Annotations.HandshakeEvent;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
@HandshakeEvent
public class SSOTicketEvent implements IMessageEventHandler {
    @Inject
    private IHabboLoginProvider loginProvider;

    @Inject
    private IThreadManager threadManager;

    @Override
    public int getId() {
        return EventHeaders.SSOTicketEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        final String ticket = event.readString();

        this.threadManager.getHabboLoginExecutor().submit(() -> {
            if(!this.loginProvider.canLogin(session, ticket)) {
                return;
            }

            this.loginProvider.attemptLogin(session, ticket);
        });
    }
}
