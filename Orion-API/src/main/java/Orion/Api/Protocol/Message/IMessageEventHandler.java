package Orion.Api.Protocol.Message;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Parser.IEventParser;

public interface IMessageEventHandler {
    int getId();

    void handle(ISession session);

    IEventParser getParser();
}
