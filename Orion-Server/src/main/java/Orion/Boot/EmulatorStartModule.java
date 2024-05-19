package Orion.Boot;

import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Api.Server.Core.Configuration.IEmulatorEnvironmentSettings;
import Orion.Api.Server.Game.Achievement.IAchievementManager;
import Orion.Api.Server.Game.HotelView.IHotelViewManager;
import Orion.Api.Server.Game.Navigator.INavigatorManager;
import Orion.Api.Server.Game.Permission.IPermissionManager;
import Orion.Api.Server.Game.Room.IRoomManager;
import Orion.Api.Server.Game.Room.Object.Item.IRoomItemManager;
import Orion.Api.Server.Task.IThreadManager;
import Orion.Api.Storage.Connector.IConnector;
import Orion.Boot.Utils.EmulatorRuntimeVariables;
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

    @Inject
    private EmulatorRuntimeVariables runtimeVariables;

    @Inject
    private IPermissionManager permissionManager;

    @Inject
    private IAchievementManager achievementManager;

    @Inject
    private IThreadManager threadManager;

    @Inject
    private IHotelViewManager hotelViewManager;

    @Inject
    private INavigatorManager navigatorManager;

    @Inject
    private IRoomManager roomManager;

    @Inject
    private IRoomItemManager roomItemManager;

    public void start() {
        this.versioning.showFullVersionWithWebsite();

        this.initEmulatorInternal();

        this.initInitialEmulatorData();

        this.networkManager.dispatch();

        System.gc();
    }

    private void initEmulatorInternal() {
        this.environmentSettings.initialize();

        this.runtimeVariables.isDebugMode = this.environmentSettings.getString("debug.mode").equalsIgnoreCase("development");

        this.emulatorConnector.initialize(this.environmentSettings);
    }

    private void initInitialEmulatorData() {
        this.emulatorDatabaseSettings.initialize();
        this.threadManager.initialize();
        this.permissionManager.initialize();
        this.achievementManager.initialize();
        this.hotelViewManager.initialize();
        this.roomItemManager.initialize();
        this.roomManager.initialize();
        this.navigatorManager.initialize();
    }
}
