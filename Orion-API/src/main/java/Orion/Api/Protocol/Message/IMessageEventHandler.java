package Orion.Api.Protocol.Message;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;

public interface IMessageEventHandler {
    int getId();

    void handle(IMessageEvent event, ISession session);
}
