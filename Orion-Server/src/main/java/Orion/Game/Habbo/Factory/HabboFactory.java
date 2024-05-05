package Orion.Game.Habbo.Factory;

import Orion.Api.Server.Game.Habbo.Data.*;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Storage.Result.IConnectionResult;
import Orion.Game.Habbo.Data.*;
import Orion.Game.Habbo.Habbo;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class HabboFactory {
    private final Logger logger = LogManager.getLogger();

    @Inject
    private Injector injector;

    @Inject
    private HabboRoomsFactory habboRoomsFactory;

    @Inject
    private HabboCurrenciesFactory habboCurrenciesFactory;

    @Inject
    private HabboNavigatorFactory habboNavigatorFactory;

    public IHabbo createHabbo(IConnectionResult databaseData) {
        try {
            final int habboId = databaseData.getInt("id");

            final IHabboData data = new HabboData(databaseData);
            final IHabboSettings settings = new HabboSettings(databaseData);
            final IHabboInventory inventory = new HabboInventory();
            final IHabboNavigator navigator = this.habboNavigatorFactory.create(databaseData);
            final IHabboCurrencies currencies = this.habboCurrenciesFactory.create(habboId);
            final IHabboRooms rooms = this.habboRoomsFactory.create(habboId);

            this.injector.injectMembers(inventory);
            this.injector.injectMembers(navigator);
            this.injector.injectMembers(currencies);

            return new Habbo(data, settings, inventory, navigator, rooms, currencies);
        } catch (Exception e) {
            this.logger.error("Error creating Habbo: {}", e.getMessage());
        }

        return null;
    }


}
