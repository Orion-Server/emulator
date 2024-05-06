package Orion.Module;

import Orion.Api.Server.Game.Messenger.IMessengerManager;
import Orion.Game.Messenger.MessengerManager;
import com.google.inject.AbstractModule;

public class MessengerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IMessengerManager.class).to(MessengerManager.class);
    }
}
