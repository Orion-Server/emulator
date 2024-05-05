package Orion.Api.Storage;

import Orion.Api.Storage.Provider.IConnectionProvider;

public interface IConnectionContext {
    IConnectionProvider getProvider();
}
