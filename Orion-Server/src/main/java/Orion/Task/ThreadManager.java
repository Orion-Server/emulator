package Orion.Task;

import Orion.Api.Server.Core.Configuration.IEmulatorEnvironmentSettings;
import Orion.Api.Server.Task.IThreadManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Singleton
public class ThreadManager implements IThreadManager {
    private final Logger logger = LogManager.getLogger();

    @Inject
    private IEmulatorEnvironmentSettings environmentSettings;

    private ExecutorService habboLoginExecutor;

    @Override
    public void initialize() {
        this.habboLoginExecutor = Executors.newFixedThreadPool(this.environmentSettings.getInteger("game.login_provider.threads"));

        this.logger.debug("Emulator thread manager initialized successfully.");
    }

    @Override
    public ExecutorService getHabboLoginExecutor() {
        return this.habboLoginExecutor;
    }
}
