package Orion.Game.Habbo;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Server.Game.Habbo.Data.*;
import Orion.Api.Server.Game.Habbo.IHabbo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Habbo implements IHabbo {
    private final Logger logger;

    private ISession session;

    private IHabboData data;
    private IHabboSettings settings;
    private IHabboInventory inventory;
    private IHabboNavigator navigator;
    private IHabboRooms rooms;
    private IHabboCurrencies currencies;
    private IHabboAchievements achievements;
    private IHabboPermission permission;

    public Habbo(
            final IHabboData data,
            final IHabboSettings settings,
            final IHabboInventory inventory,
            final IHabboNavigator navigator,
            final IHabboRooms rooms,
            final IHabboCurrencies currencies,
            final IHabboAchievements achievements,
            final IHabboPermission permission
    ) {
        this.data = data;
        this.settings = settings;
        this.inventory = inventory;
        this.navigator = navigator;
        this.rooms = rooms;
        this.currencies = currencies;
        this.achievements = achievements;
        this.permission = permission;

        this.logger = LogManager.getLogger(STR."[Habbo: \{this.data.getUsername()}]");
    }

    @Override
    public void setSession(ISession session) {
        this.session = session;
    }

    @Override
    public ISession getSession() {
        return this.session;
    }

    @Override
    public IHabboData getData() {
        return this.data;
    }

    @Override
    public IHabboSettings getSettings() {
        return this.settings;
    }

    @Override
    public IHabboInventory getInventory() {
        return this.inventory;
    }

    @Override
    public IHabboNavigator getNavigator() {
        return this.navigator;
    }

    @Override
    public IHabboRooms getRooms() {
        return this.rooms;
    }

    @Override
    public IHabboCurrencies getCurrencies() {
        return this.currencies;
    }

    @Override
    public IHabboAchievements getAchievements() {
        return this.achievements;
    }

    @Override
    public IHabboPermission getPermission() {
        return this.permission;
    }

    @Override
    public void onDisconnect() {
        this.logger.debug("Just left the game.");

        this.data = null;
        this.permission = null;

        this.settings.dispose();
        this.settings = null;

        this.navigator.dispose();
        this.navigator = null;

        this.rooms.dispose();
        this.rooms = null;

        this.currencies.dispose();
        this.currencies = null;

        this.inventory.dispose();
        this.inventory = null;

        this.achievements.dispose();
        this.achievements = null;
    }
}
