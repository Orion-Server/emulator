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

    @Inject
    private HabboAchievementsFactory habboAchievementsFactory;

    @Inject
    private HabboMessengerFactory habboMessengerFactory;

    @Inject
    private HabboInventoryFactory habboInventoryFactory;

    public IHabbo create(IConnectionResult habboData) {
        try {
            final int habboId = habboData.getInt("id");

            final IHabboData data = new HabboData(habboData);
            final IHabboInventory inventory = this.habboInventoryFactory.create();
            final IHabboSettings settings = new HabboSettings(habboData);

            final IHabboRooms rooms = this.habboRoomsFactory.create(habboId);
            final IHabboCurrencies currencies = this.habboCurrenciesFactory.create(habboId);
            final IHabboNavigator navigator = this.habboNavigatorFactory.create(habboData);
            final IHabboAchievements achievements = this.habboAchievementsFactory.create(habboId);
            final IHabboPermission permission = new HabboPermission();
            final IHabboMessenger messenger = this.habboMessengerFactory.create(habboId);

            this.injector.injectMembers(inventory);
            this.injector.injectMembers(navigator);
            this.injector.injectMembers(currencies);
            this.injector.injectMembers(permission);

            final IHabbo habbo = new Habbo(data, settings, inventory, navigator, rooms, currencies, achievements, permission, messenger);

            // Do things with the logged habbo instance before returning it

            permission.setHabbo(habbo);

            return habbo;
        } catch (Exception e) {
            this.logger.error("Error creating Habbo: {}", e.getMessage());
        }

        return null;
    }


}
