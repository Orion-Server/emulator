package Orion.Api.Server.Boot.Utils;

public interface IEmulatorRuntimeVariables {
    void incrementAndGetPlayersOnline();

    int getPlayersOnline();
}
