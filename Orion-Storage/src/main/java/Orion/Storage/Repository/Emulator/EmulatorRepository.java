package Orion.Storage.Repository.Emulator;

import Orion.Api.Storage.Repository.Emulator.IEmulatorRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.Emulator.EmulatorQuery;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Singleton;

@Singleton
public class EmulatorRepository extends DatabaseRepository implements IEmulatorRepository {
    @Override
    public void loadAllSettings(final IConnectionResultConsumer consumer) {
        this.select(EmulatorQuery.LOAD_ALL_SETTINGS.get(), consumer);
    }
}
