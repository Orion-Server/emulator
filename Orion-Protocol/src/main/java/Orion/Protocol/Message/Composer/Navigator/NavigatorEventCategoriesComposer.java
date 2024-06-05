package Orion.Protocol.Message.Composer.Navigator;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Navigator.INavigatorManager;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class NavigatorEventCategoriesComposer extends Composer {
    private final INavigatorManager navigatorManager;

    public NavigatorEventCategoriesComposer(final INavigatorManager navigatorManager) {
        this.navigatorManager = navigatorManager;
    }

    @Override
    public short getId() {
        return ComposerHeaders.NavigatorEventCategoriesComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(navigatorManager.getEventCategories().size());

        navigatorManager.getEventCategories().forEach((_, category) -> {
            msg.appendInt(category.getId());
            msg.appendString(category.getName());
            msg.appendBoolean(category.isVisible());
        });
    }
}