package Orion.Api.Protocol.Message;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Parser.IEventParser;

public interface IMessageEventHandler {
    int getId();

    IEventParser getParser();

    void handle(ISession session);
}
