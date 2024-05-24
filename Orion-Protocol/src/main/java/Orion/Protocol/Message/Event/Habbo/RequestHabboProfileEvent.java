package Orion.Protocol.Message.Event.Habbo;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Habbo.IHabboManager;
import Orion.Protocol.Message.Composer.Habbo.HabboProfileComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestHabboProfileEvent implements IMessageEventHandler {
    @Inject
    private IHabboManager habboManager;

    @Override
    public int getId() {
        return EventHeaders.RequestHabboProfileEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        final int habboId = event.readInt();

        IHabbo habbo = this.habboManager.getConnectedHabboById(habboId);

        if(habbo == null) {
            // TODO: Implement
            return;
        }

        session.send(new HabboProfileComposer(session.getHabbo(), habbo));
    }
}
