package Orion.Protocol.Message.Event.Habbo;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Habbo.IHabboManager;
import Orion.Protocol.Message.Composer.Habbo.HabboProfileComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Habbo.RequestHabboProfileEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.ArrayList;

@Singleton
public class RequestHabboProfileEvent implements IMessageEventHandler {
    @Inject
    private IHabboManager habboManager;

    @Inject
    private RequestHabboProfileEventParser parser;

    @Override
    public int getId() {
        return EventHeaders.RequestHabboProfileEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    public void handle(ISession session) {
        IHabbo habbo = this.habboManager.getConnectedHabboById(this.parser.habboId);

        if(habbo == null) {
            // TODO: Implement
            return;
        }

        session.send(new HabboProfileComposer(session.getHabbo(), habbo));
    }
}
