package Orion.Api.Protocol.Parser;

public interface IEventParserProvider {
    IEventParser getParserByHeaderId(int id);
}
