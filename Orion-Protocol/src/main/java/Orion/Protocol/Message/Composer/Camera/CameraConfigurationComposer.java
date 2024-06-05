package Orion.Protocol.Message.Composer.Camera;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class CameraConfigurationComposer extends Composer {
    private final IEmulatorDatabaseSettings databaseSettings;

    public CameraConfigurationComposer(final IEmulatorDatabaseSettings databaseSettings) {
        this.databaseSettings = databaseSettings;
    }

    @Override
    public short getId() {
        return ComposerHeaders.CameraConfigurationComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.databaseSettings.getIntegerOrDefault("camera.price.credits", 0));
        msg.appendInt(this.databaseSettings.getIntegerOrDefault("camera.price.points", 0));
        msg.appendInt(this.databaseSettings.getIntegerOrDefault("camera.price.points.publish", 0));
    }
}
