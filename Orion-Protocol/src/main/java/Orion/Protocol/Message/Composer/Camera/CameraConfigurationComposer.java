package Orion.Protocol.Message.Composer.Camera;

import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class CameraConfigurationComposer extends MessageComposer {
    public CameraConfigurationComposer(
            final IEmulatorDatabaseSettings databaseSettings
    ) {
        super(ComposerHeaders.CameraConfigurationComposer);

        appendInt(databaseSettings.getIntegerOrDefault("camera.price.credits", 0));
        appendInt(databaseSettings.getIntegerOrDefault("camera.price.points", 0));
        appendInt(databaseSettings.getIntegerOrDefault("camera.price.points.publish", 0));
    }
}
