package Orion.Api.Util;

import Orion.Api.Storage.Result.IConnectionResult;

public interface IFillable {
    void fill(IConnectionResult data) throws Exception;
}
