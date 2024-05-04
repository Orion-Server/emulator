package Orion.Protocol.Parser;

import Orion.Api.Protocol.Parser.IEventParser;

public @interface EventParser {
    Class<IEventParser> value();
}
