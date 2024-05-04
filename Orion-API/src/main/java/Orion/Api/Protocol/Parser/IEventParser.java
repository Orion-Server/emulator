package Orion.Api.Protocol.Parser;

import Orion.Api.Networking.Message.IMessageEvent;

public interface IEventParser {
    int getId();

    void parse(IMessageEvent messageEvent);
}
