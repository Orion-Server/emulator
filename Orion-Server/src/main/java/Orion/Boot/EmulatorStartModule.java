package Orion.Boot;

import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Api.Server.Core.Configuration.IEmulatorEnvironmentSettings;
import Orion.Api.Storage.Connector.IConnector;
import Orion.Api.Storage.Repository.Emulator.IEmulatorRepository;
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

    @Inject
    private IConnector emulatorConnector;

    @Inject
    private IEmulatorDatabaseSettings emulatorDatabaseSettings;

    public void start() {
        this.versioning.showFullVersionWithWebsite();

        this.initEmulatorInternal();
        this.initInitialEmulatorData();

        this.networkManager.dispatch();
    }

    private void initEmulatorInternal() {
        this.environmentSettings.initialize();
        this.emulatorConnector.initialize(this.environmentSettings);
    }

    private void initInitialEmulatorData() {
        this.emulatorDatabaseSettings.initialize();
    }
}
