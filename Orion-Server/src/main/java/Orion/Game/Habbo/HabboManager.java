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

    public void addHabbo(IHabbo habbo) {
//        this.connectedHabbos.putIfAbsent(habbo.getId(), habbo);
    }

    @Override
    public boolean hasLoggedHabboById(int habboId) {
        return this.connectedHabbos.containsKey(habboId);
    }
}
