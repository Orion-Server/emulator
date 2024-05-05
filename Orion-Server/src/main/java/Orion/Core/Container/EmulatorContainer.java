package Orion.Core.Container;

import Orion.Api.Server.Boot.IEmulator;
import Orion.Api.Server.Core.Container.IEmulatorContainer;
import Orion.Module.*;
import Orion.Protocol.ProtocolModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class EmulatorContainer implements IEmulatorContainer {
    private Injector injector;

    private IEmulator emulator;

    public void initialize(final IEmulator emulator) {
        this.emulator = emulator;

        this.injector = Guice.createInjector(
                new EmulatorModule(),
                new ConnectionModule(),
                new NetworkingModule(),
                new ProtocolModule(),
                new HabboModule(),
                new RoomModule()
        );

        this.inject(this.emulator);
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        return this.injector.getInstance(type);
    }

    @Override
    public void inject(Object object) {
        this.injector.injectMembers(object);
    }
}
