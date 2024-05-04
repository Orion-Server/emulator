package Orion.Api.Storage;

import Orion.Api.Storage.Providers.IConnectionProvider;

public interface IConnectionContext {
    IConnectionProvider getProvider();
}
