package Orion.Boot;

import Orion.Api.Server.Core.Configuration.IEmulatorEnvironmentSettings;
import Orion.Boot.Utils.EmulatorVersioning;
import Orion.Networking.NetworkManager;
import com.google.inject.Inject;

public class EmulatorStartModule {
    @Inject
    private EmulatorVersioning versioning;

    @Inject
    private IEmulatorEnvironmentSettings environmentSettings;

    @Inject
    private NetworkManager networkManager;

    public void start() {
        this.versioning.showFullVersionWithWebsite();

        this.environmentSettings.initialize();

        this.networkManager.dispatch();
    }
}
