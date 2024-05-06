package Orion.Game.Habbo;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Habbo.IHabboManager;
import com.google.inject.Singleton;

import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class HabboManager implements IHabboManager {
    private final ConcurrentHashMap<Integer, IHabbo> connectedHabbos;

    public HabboManager() {
        this.connectedHabbos = new ConcurrentHashMap<>();
    }

    @Override
    public void addHabbo(IHabbo habbo) {
        this.connectedHabbos.putIfAbsent(habbo.getData().getId(), habbo);
    }

    @Override
    public void removeHabbo(IHabbo habbo) {
        this.connectedHabbos.remove(habbo.getData().getId());
    }

    @Override
    public void disposeHabbo(IHabbo habbo) {
        this.removeHabbo(habbo);

        habbo.onDisconnect();
    }

    @Override
    public IHabbo getConnectedHabboById(int habboId) {
        return this.connectedHabbos.get(habboId);
    }

    @Override
    public boolean hasLoggedHabboById(int habboId) {
        return this.connectedHabbos.containsKey(habboId);
    }
}
