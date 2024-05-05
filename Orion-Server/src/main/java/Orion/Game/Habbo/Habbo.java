package Orion.Game.Habbo;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Server.Game.Habbo.Data.*;
import Orion.Api.Server.Game.Habbo.IHabbo;

public class Habbo implements IHabbo {
    private ISession session;

    private final IHabboData data;
    private final IHabboSettings settings;
    private final IHabboInventory inventory;
    private final IHabboNavigator navigator;
    private final IHabboRooms rooms;
    private final IHabboCurrencies currencies;

    public Habbo(
            final IHabboData data,
            final IHabboSettings settings,
            final IHabboInventory inventory,
            final IHabboNavigator navigator,
            final IHabboRooms rooms,
            final IHabboCurrencies currencies
    ) {
        this.data = data;
        this.settings = settings;
        this.inventory = inventory;
        this.navigator = navigator;
        this.rooms = rooms;
        this.currencies = currencies;
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
}
