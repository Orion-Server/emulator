package Orion.Module;

import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Api.Server.Core.Configuration.IEmulatorEnvironmentSettings;
import Orion.Api.Server.Task.IThreadManager;
import Orion.Boot.Utils.EmulatorRuntimeVariables;
import Orion.Boot.Utils.EmulatorVersioning;
import Orion.Core.Configuration.EmulatorDatabaseSettings;
import Orion.Core.Configuration.EmulatorEnvironmentSettings;
import Orion.Task.ThreadManager;
import com.google.inject.AbstractModule;

public class EmulatorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(EmulatorVersioning.class).asEagerSingleton();
        bind(EmulatorRuntimeVariables.class).asEagerSingleton();

        bind(IEmulatorEnvironmentSettings.class).to(EmulatorEnvironmentSettings.class);
        bind(IEmulatorDatabaseSettings.class).to(EmulatorDatabaseSettings.class);

        bind(IThreadManager.class).to(ThreadManager.class);
    }
}
