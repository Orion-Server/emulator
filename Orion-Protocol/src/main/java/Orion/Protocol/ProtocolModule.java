package Orion.Protocol;

import Orion.Api.Protocol.IServerMessageHandler;
import Orion.Api.Protocol.Message.Event.IMessageEventProvider;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Protocol.Parser.IEventParserProvider;
import Orion.Protocol.Message.Event.MessageEventProvider;
import Orion.Protocol.Parser.EventParserProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.reflections.Reflections;

@Singleton
public class ProtocolModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IServerMessageHandler.class).to(ServerMessageHandler.class);

        bind(IMessageEventProvider.class).to(MessageEventProvider.class);
        bind(IEventParserProvider.class).to(EventParserProvider.class);

        this.bindWithReflections(MessageEventProvider.class.getPackageName(), IMessageEventHandler.class);
        this.bindWithReflections(EventParserProvider.class.getPackageName(), IEventParser.class);
    }

    private void bindWithReflections(String packageName, Class<?> interfaceType) {
        final Reflections reflections = new Reflections(packageName);
        final var subtypesOfMessageEventHandler = reflections.getSubTypesOf(interfaceType);

        for (var messageEventHandler : subtypesOfMessageEventHandler) {
            bind(messageEventHandler).asEagerSingleton();
        }
    }
}
