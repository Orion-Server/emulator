package Orion.Api.Server.Game.Habbo;

public interface IHabboManager {
    void addHabbo(IHabbo habbo);

    IHabbo getConnectedHabboById(int habboId);

    boolean hasLoggedHabboById(int habboId);

    void removeHabbo(IHabbo habbo);

    void disposeHabbo(IHabbo habbo);
}
