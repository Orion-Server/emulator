package Orion.Protocol.Message.Event.Habbo.Inventory;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Composer.Habbo.Inventory.InventoryBotsComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestInventoryBotsEvent implements IMessageEventHandler {
    @Override
    public int getId() {
        return EventHeaders.RequestInventoryBotsEvent;
    }

    @Override
    public IEventParser getParser() {
        return null;
    }

    @Override
    public void handle(ISession session) {
        session.send(new InventoryBotsComposer(session.getHabbo().getInventory().getBotsComponent()));
    }
}
