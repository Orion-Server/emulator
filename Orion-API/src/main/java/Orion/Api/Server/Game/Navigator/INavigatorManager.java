package Orion.Api.Server.Game.Navigator;

import Orion.Api.Server.Game.Navigator.Data.INavigatorEventCategory;
import Orion.Api.Server.Game.Navigator.Data.INavigatorFilterType;
import Orion.Api.Server.Game.Navigator.Data.INavigatorPublicCategory;
import Orion.Api.Server.Game.Navigator.Tabs.INavigatorTab;
import Orion.Api.Util.Initializable;
import gnu.trove.map.hash.THashMap;

public interface INavigatorManager extends Initializable {
    THashMap<Integer, INavigatorEventCategory> getEventCategories();

    THashMap<Integer, INavigatorPublicCategory> getPublicCategories();

    INavigatorPublicCategory getPublicCategoryById(int id);

    INavigatorTab getTab(String tabName);

    INavigatorFilterType getFilterTypeByKey(String key);

    INavigatorFilterType getReplaceableFilterTypeByKey(String key, String fallbackKey);
}
