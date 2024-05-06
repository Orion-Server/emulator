package Orion.Protocol.Message.Event.Camera;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Protocol.Message.Composer.Camera.CameraConfigurationComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;

public class RequestCameraConfigurationEvent implements IMessageEventHandler {
    @Inject
    private IEmulatorDatabaseSettings databaseSettings;

    @Override
    public int getId() {
        return EventHeaders.RequestCameraConfigurationEvent;
    }

    @Override
    public IEventParser getParser() {
        return null;
    }

    @Override
    public void handle(ISession session) {
        session.send(new CameraConfigurationComposer(databaseSettings));
    }
}
