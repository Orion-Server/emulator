package Orion.Api.Server.Core.Container;

import Orion.Api.Server.Boot.IEmulator;

public interface IEmulatorContainer {
    <T> T getInstance(Class<T> type);

    void inject(Object object);

    void initialize(final IEmulator emulator);
}
