package Orion.Protocol.Message.Event.Habbo.Inventory;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Habbo.Data.Inventory.IHabboInventoryItem;
import Orion.Api.Server.Game.Habbo.Factory.IHabboInventoryFactory;
import Orion.Api.Server.Task.IThreadManager;
import Orion.Protocol.Message.Composer.Habbo.Inventory.InventoryItemsComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Collection;
import java.util.Collections;

@Singleton
public class RequestInventoryItemsEvent implements IMessageEventHandler {
    @Inject
    private IThreadManager threadManager;

    @Inject
    private IHabboInventoryFactory habboInventoryFactory;

    @Override
    public int getId() {
        return EventHeaders.RequestInventoryItemsEvent;
    }

    @Override
    public IEventParser getParser() {
        return null;
    }

    @Override
    public void handle(ISession session) {
        this.threadManager.getHabboLoginExecutor().submit(() -> {
            this.habboInventoryFactory.loadAllHabboInventory(session.getHabbo());

            final Collection<IHabboInventoryItem> items = session.getHabbo().getInventory().getItemsComponent().getItems();

            if (items.isEmpty()) {
                session.send(new InventoryItemsComposer(0, 1, Collections.emptyList()));
                return;
            }

            final int limitPerPage = 1000; // TODO: Move it and maybe improve this logic?

            if (items.size() <= limitPerPage) {
                session.send(new InventoryItemsComposer(0, 1, items));
                return;
            }

            final int totalPagesCount = Math.max((int) Math.ceil((double) items.size() / limitPerPage), 1);

            for (int i = 0; i < totalPagesCount; i++) {
                final int start = i * limitPerPage;
                final int end = Math.min((i + 1) * limitPerPage, items.size());

                session.send(new InventoryItemsComposer(i, totalPagesCount, items.stream().skip(start).limit(end).toList()));
            }
        });
    }
}
