package Orion.Protocol.Message.Event;

import Orion.Api.Protocol.Message.Event.IMessageEventProvider;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import gnu.trove.map.hash.THashMap;
import org.reflections.Reflections;

import java.util.Set;

@Singleton
public class MessageEventProvider implements IMessageEventProvider {
    private final Injector injector;

    private final THashMap<Integer, IMessageEventHandler> messageEventList;

    @Inject
    public MessageEventProvider(Injector injector) {
        this.injector = injector;

        this.messageEventList = new THashMap<>();

        this.loadMessageEvents();
    }

    private void loadMessageEvents() {
        final Reflections reflections = new Reflections(MessageEventProvider.class.getPackageName());
        final Set<Class<? extends IMessageEventHandler>> subtypesOfMessageEventHandler = reflections.getSubTypesOf(IMessageEventHandler.class);

        if(injector == null) return;

        for (Class<? extends IMessageEventHandler> messageEventHandler : subtypesOfMessageEventHandler) {
            final IMessageEventHandler eventInstance = injector.getInstance(messageEventHandler);

            if(eventInstance == null || this.messageEventList.contains(eventInstance)) continue;

            this.messageEventList.putIfAbsent(eventInstance.getId(), eventInstance);
        }
    }

    public IMessageEventHandler getMessageEventByHeaderId(int headerId) {
        return this.messageEventList.get(headerId);
    }

    public THashMap<Integer, IMessageEventHandler> getMessageEventList() {
        return this.messageEventList;
    }

    public boolean containsMessageEvent(int headerId) {
        return this.messageEventList.containsKey(headerId);
    }

    public void reloadMessageEvents() {
        this.messageEventList.clear();
        this.loadMessageEvents();
    }
}
