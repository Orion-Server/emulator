package Orion.Protocol.Message.Composer.Habbo.Messenger;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerCategory;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class InitializeMessengerComposer extends Composer {
    private final IHabbo habbo;

    public InitializeMessengerComposer(final IHabbo habbo) {
        this.habbo = habbo;
    }

    @Override
    public short getId() {
        return ComposerHeaders.InitializeMessengerComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        this.writeMessengerLimits(msg, habbo.getPermission().hasAccountPermission("infinite_friends"));

        msg.appendInt(habbo.getMessenger().getCategories().size());

        for (final IMessengerCategory category : habbo.getMessenger().getCategories()) {
            msg.appendInt(category.getId());
            msg.appendString(category.getName());
        }
    }

    private void writeMessengerLimits(IMessageComposer msg, final boolean hasInfiniteFriendsPermission) {
        // TODO: Fetch these values from the database - Non-HC messenger / HC Messenger
        msg.appendInt(hasInfiniteFriendsPermission ? Integer.MAX_VALUE : 200);
        msg.appendInt(0x539);
        msg.appendInt(hasInfiniteFriendsPermission ? Integer.MAX_VALUE : 500);
    }
}