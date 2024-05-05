package Orion.Boot;

import Orion.Api.Server.Boot.IEmulator;
import Orion.Api.Server.Core.Container.IEmulatorContainer;
import Orion.Boot.Utils.EmulatorRuntimeVariables;
import Orion.Core.Container.EmulatorContainer;
import com.google.inject.Inject;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;

import static java.lang.StringTemplate.STR;

public class Emulator implements IEmulator {
    private final Logger logger = LogManager.getLogger();

    private final IEmulatorContainer container;

    @Inject
    private EmulatorRuntimeVariables runtimeVariables;

    public Emulator() {
        this.container = new EmulatorContainer();

        try {
            container.initialize(this);

            this.start();
        } catch (Exception e) {
            System.out.println(STR."Failed to start emulator modules: \{e.getMessage()}");
        }
    }

    @Override
    public void start() {
        this.configureLogger();

        this.runtimeVariables.startTime = System.currentTimeMillis();

        final EmulatorStartModule startProcess = new EmulatorStartModule();

        this.container.inject(startProcess);

        startProcess.start();
    }

    private void configureLogger() {
        Configurator.setRootLevel(Level.DEBUG);

        final File log4j2File = new File("configuration/log4j2.xml");
        final LoggerContext context = (LoggerContext) LogManager.getContext(false);

        context.setConfigLocation(log4j2File.toURI());
    }

    @Override
    public IEmulatorContainer getContainer() {
        return this.container;
    }
}