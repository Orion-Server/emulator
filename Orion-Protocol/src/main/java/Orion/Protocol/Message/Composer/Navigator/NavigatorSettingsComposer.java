package Orion.Protocol.Message.Composer.Navigator;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.Data.Navigator.IHabboNavigatorWindowSetting;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class NavigatorSettingsComposer extends Composer {
    private final IHabboNavigatorWindowSetting navigatorWindowSetting;

    public NavigatorSettingsComposer(final IHabboNavigatorWindowSetting navigatorWindowSetting) {
        this.navigatorWindowSetting = navigatorWindowSetting;
    }

    @Override
    public short getId() {
        return ComposerHeaders.NavigatorSettingsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.navigatorWindowSetting.getWindowX());
        msg.appendInt(this.navigatorWindowSetting.getWindowY());
        msg.appendInt(this.navigatorWindowSetting.getWindowWidth());
        msg.appendInt(this.navigatorWindowSetting.getWindowHeight());
        msg.appendBoolean(this.navigatorWindowSetting.isLeftPanelCollapsed());
        msg.appendInt(0);
    }
}
