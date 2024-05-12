package Orion.Api.Server.Task;

import Orion.Api.Util.Initializable;

import java.util.concurrent.ExecutorService;

public interface IThreadManager extends Initializable {
    ExecutorService getHabboLoginExecutor();

    ExecutorService getRoomProcessingExecutor();
}
