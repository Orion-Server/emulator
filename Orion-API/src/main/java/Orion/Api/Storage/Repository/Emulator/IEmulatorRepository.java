package Orion.Api.Storage.Repository.Emulator;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IEmulatorRepository {
    void loadAllSettings(IConnectionResultConsumer consumer);
}
