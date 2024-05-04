package Orion.Api.Protocol;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;

public interface IServerMessageHandler {
    void handle(ISession session, IMessageEvent message);
}
