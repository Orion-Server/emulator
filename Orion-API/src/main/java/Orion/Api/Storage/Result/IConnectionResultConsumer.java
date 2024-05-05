package Orion.Api.Storage.Result;

public interface IConnectionResultConsumer {
    void accept(IConnectionResult result) throws Exception;
}
