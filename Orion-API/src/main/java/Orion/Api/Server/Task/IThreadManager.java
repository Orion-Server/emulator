package Orion.Api.Server.Task;

import Orion.Api.Util.Initializable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public interface IThreadManager extends Initializable {
    ExecutorService getHabboLoginExecutor();

    ScheduledExecutorService getRoomProcessingExecutor();
}
