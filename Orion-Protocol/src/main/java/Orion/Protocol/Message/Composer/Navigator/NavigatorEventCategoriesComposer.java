package Orion.Protocol.Message.Composer.Navigator;

import Orion.Api.Server.Game.Navigator.INavigatorManager;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class NavigatorEventCategoriesComposer extends MessageComposer {
    public NavigatorEventCategoriesComposer(final INavigatorManager navigatorManager) {
        super(ComposerHeaders.NavigatorEventCategoriesComposer);

        appendInt(navigatorManager.getEventCategories().size());

        navigatorManager.getEventCategories().forEach((_, category) -> {
            appendInt(category.getId());
            appendString(category.getName());
            appendBoolean(category.isVisible());
        });
    }
}