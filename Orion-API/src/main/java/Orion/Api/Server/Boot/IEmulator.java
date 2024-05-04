package Orion.Api.Server.Boot;

import Orion.Api.Server.Core.Container.IEmulatorContainer;

public interface IEmulator {
    void start();

    IEmulatorContainer getContainer();
}
