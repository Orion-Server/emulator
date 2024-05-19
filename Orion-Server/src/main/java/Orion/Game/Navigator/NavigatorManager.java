package Orion.Game.Navigator;

import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Api.Server.Game.Navigator.Data.INavigatorEventCategory;
import Orion.Api.Server.Game.Navigator.Data.INavigatorFilterType;
import Orion.Api.Server.Game.Navigator.Data.INavigatorPublicCategory;
import Orion.Api.Server.Game.Navigator.INavigatorManager;
import Orion.Api.Server.Game.Navigator.Tabs.INavigatorTab;
import Orion.Api.Storage.Repository.Navigator.INavigatorRepository;
import Orion.Game.Navigator.Data.NavigatorEventCategory;
import Orion.Game.Navigator.Data.NavigatorFilterType;
import Orion.Game.Navigator.Data.NavigatorPublicCategory;
import Orion.Game.Navigator.Tab.NavigatorEventsTab;
import Orion.Game.Navigator.Tab.NavigatorHabboTab;
import Orion.Game.Navigator.Tab.NavigatorOfficialTab;
import Orion.Game.Navigator.Tab.NavigatorRecommendedTab;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import gnu.trove.map.hash.THashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class NavigatorManager implements INavigatorManager {
    private final Logger logger = LogManager.getLogger();

    private final THashMap<String, INavigatorFilterType> filterTypes;

    private final THashMap<Integer, INavigatorEventCategory> eventCategories;

    private final THashMap<String, INavigatorTab> tabs;

    private final THashMap<Integer, INavigatorPublicCategory> publicCategories;

    @Inject
    private IEmulatorDatabaseSettings databaseSettings;

    @Inject
    private INavigatorRepository repository;

    @Inject
    private Injector injector;

    public NavigatorManager() {
        this.tabs = new THashMap<>();
        this.filterTypes = new THashMap<>();
        this.eventCategories = new THashMap<>();
        this.publicCategories = new THashMap<>();
    }

    @Override
    public void initialize() {
        this.loadPublicCategories();
        this.loadFilterTypes();
        this.loadEventCategories();
        this.registerTabs();
    }

    @Override
    public THashMap<Integer, INavigatorEventCategory> getEventCategories() {
        return this.eventCategories;
    }

    @Override
    public THashMap<Integer, INavigatorPublicCategory> getPublicCategories() {
        return this.publicCategories;
    }

    @Override
    public INavigatorPublicCategory getPublicCategoryById(int id) {
        return this.publicCategories.get(id);
    }

    @Override
    public INavigatorFilterType getFilterTypeByKey(String key) {
        return this.filterTypes.get(key);
    }

    @Override
    public INavigatorFilterType getReplaceableFilterTypeByKey(String key, String fallbackKey) {
        if(key == null || key.isBlank()) {
            key = fallbackKey;
        }

        if(this.filterTypes.containsKey(key)) {
            return this.filterTypes.get(key);
        }

        return this.filterTypes.get(fallbackKey);
    }

    @Override
    public INavigatorTab getTab(String tabName) {
        return this.tabs.get(tabName);
    }

    private void loadPublicCategories() {
        this.repository.loadPublicCategories(result -> {
            if(result == null) return;

            final INavigatorPublicCategory category = new NavigatorPublicCategory(result);

            this.publicCategories.put(category.getId(), category);
        });

        logger.info("Loaded {} navigator public categories", this.publicCategories.size());
    }

    private void loadFilterTypes() {
        this.repository.loadFilterTypes(result -> {
            if (result == null) return;

            final NavigatorFilterType filterType = new NavigatorFilterType(result);

            this.filterTypes.put(filterType.getKey(), filterType);
        });

        logger.info("Loaded {} navigator filter types", this.filterTypes.size());
    }

    private void loadEventCategories() {
        final String eventCategories = this.databaseSettings.getSettingOrDefault("navigator.eventcategories", "").trim();

        if(eventCategories.isBlank()) return;

        for(String eventCategory : eventCategories.split(";")) {
            final INavigatorEventCategory category = new NavigatorEventCategory(eventCategory);

            this.eventCategories.put(category.getId(), category);
        }

        logger.info("Loaded {} navigator event categories", this.eventCategories.size());
    }

    private void registerTabs() {
        this.tabs.putIfAbsent(NavigatorOfficialTab.FILTER_NAME, new NavigatorOfficialTab());
        this.tabs.putIfAbsent(NavigatorRecommendedTab.FILTER_NAME, new NavigatorRecommendedTab());
        this.tabs.putIfAbsent(NavigatorEventsTab.FILTER_NAME, new NavigatorEventsTab());
        this.tabs.putIfAbsent(NavigatorHabboTab.FILTER_NAME, new NavigatorHabboTab());

        this.tabs.forEach((_, tab) -> this.injector.injectMembers(tab));
    }
}
