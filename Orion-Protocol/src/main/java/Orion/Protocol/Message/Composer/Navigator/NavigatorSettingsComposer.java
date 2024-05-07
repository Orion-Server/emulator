package Orion.Protocol.Message.Composer.Navigator;

import Orion.Api.Server.Game.Habbo.Data.Navigator.IHabboNavigatorWindowSetting;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class NavigatorSettingsComposer extends MessageComposer {
    public NavigatorSettingsComposer(
            final IHabboNavigatorWindowSetting navigatorWindowSetting
    ) {
        super(ComposerHeaders.NavigatorSettingsComposer);

        appendInt(navigatorWindowSetting.getWindowX());
        appendInt(navigatorWindowSetting.getWindowY());
        appendInt(navigatorWindowSetting.getWindowWidth());
        appendInt(navigatorWindowSetting.getWindowHeight());
        appendBoolean(navigatorWindowSetting.isLeftPanelCollapsed());
        appendInt(0);
    }
}
