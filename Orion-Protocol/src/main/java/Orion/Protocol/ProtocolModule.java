package Orion.Protocol;

import Orion.Api.Protocol.IServerMessageHandler;
import Orion.Api.Protocol.Message.Event.IMessageEventProvider;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Protocol.Message.Event.MessageEventProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.reflections.Reflections;

@Singleton
public class ProtocolModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IServerMessageHandler.class).to(ServerMessageHandler.class);

        bind(IMessageEventProvider.class).to(MessageEventProvider.class);

        final Reflections reflections = new Reflections(MessageEventProvider.class.getPackageName());
        final var subtypesOfMessageEventHandler = reflections.getSubTypesOf(IMessageEventHandler.class);

        for (var messageEventHandler : subtypesOfMessageEventHandler) {
            bind(messageEventHandler).asEagerSingleton();
        }
    }
}
