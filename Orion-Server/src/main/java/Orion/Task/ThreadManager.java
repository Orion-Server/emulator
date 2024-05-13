package Orion.Task;

import Orion.Api.Server.Core.Configuration.IEmulatorEnvironmentSettings;
import Orion.Api.Server.Task.IThreadManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

@Singleton
public class ThreadManager implements IThreadManager {
    private final Logger logger = LogManager.getLogger();

    @Inject
    private IEmulatorEnvironmentSettings environmentSettings;

    private ExecutorService habboLoginExecutor;

    private ScheduledExecutorService roomProcessingExecutor;

    @Override
    public void initialize() {
        this.habboLoginExecutor = Executors.newFixedThreadPool(
                this.environmentSettings.getInteger("game.login_provider.threads"),
                this.getThreadFactory("HabboLoginProvider")
        );
        
        this.roomProcessingExecutor = Executors.newScheduledThreadPool(
                this.environmentSettings.getInteger("game.room_processing.threads"),
                this.getThreadFactory("RoomProcess")
        );

        this.logger.debug("Emulator thread manager initialized successfully.");
    }

    private ThreadFactory getThreadFactory(final String name) {
        return r -> {
            final Thread fixedThread = new Thread(r);
            final Logger logger = LogManager.getLogger(STR."\{name}Thread");

            fixedThread.setName("Orion-LoginProvider-Thread");
            fixedThread.setUncaughtExceptionHandler((_, e) -> logger.error(STR."An error occurred in the \{name} thread.", e));

            return fixedThread;
        };
    }

    @Override
    public ExecutorService getHabboLoginExecutor() {
        return this.habboLoginExecutor;
    }

    @Override
    public ScheduledExecutorService getRoomProcessingExecutor() {
        return this.roomProcessingExecutor;
    }
}
