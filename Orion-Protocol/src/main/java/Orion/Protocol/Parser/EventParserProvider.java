package Orion.Protocol.Parser;

import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Protocol.Parser.IEventParserProvider;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;
import org.reflections.Reflections;

import java.util.Set;

@Singleton
public class EventParserProvider implements IEventParserProvider {
    private final Injector injector;

    private final THashMap<Integer, IEventParser> eventParserList;

    @Inject
    public EventParserProvider(Injector injector) {
        this.injector = injector;

        this.eventParserList = new THashMap<>();

        this.loadMessageEvents();
    }

    private void loadMessageEvents() {
        final Reflections reflections = new Reflections(EventParserProvider.class.getPackageName());
        final Set<Class<? extends IEventParser>> subtypesOfEventParser = reflections.getSubTypesOf(IEventParser.class);

        if(injector == null) return;

        for (final Class<? extends IEventParser> eventParser : subtypesOfEventParser) {
            final IEventParser eventParserInstance = injector.getInstance(eventParser);

            if(eventParserInstance == null || this.eventParserList.contains(eventParserInstance)) continue;

            injector.injectMembers(eventParserInstance);

            this.eventParserList.putIfAbsent(eventParserInstance.getId(), eventParserInstance);
        }
    }

    public IEventParser getParserByHeaderId(int id) {
        return this.eventParserList.get(id);
    }
}
