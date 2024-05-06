package Orion.Protocol.Message.Composer.Habbo.Messenger;

import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerCategory;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class InitializeMessengerComposer extends MessageComposer {
    public InitializeMessengerComposer(final IHabbo habbo) {
        super(ComposerHeaders.InitializeMessengerComposer);

        this.writeMessengerLimits(habbo.getPermission().hasAccountPermission("infinite_friends"));

        appendInt(habbo.getMessenger().getCategories().size());

        for (final IMessengerCategory category : habbo.getMessenger().getCategories()) {
            appendInt(category.getId());
            appendString(category.getName());
        }
    }

    private void writeMessengerLimits(final boolean hasInfiniteFriendsPermission) {
        // TODO: Fetch these values from the database - Non-HC messenger / HC Messenger
        appendInt(hasInfiniteFriendsPermission ? Integer.MAX_VALUE : 200);
        appendInt(0x539);
        appendInt(hasInfiniteFriendsPermission ? Integer.MAX_VALUE : 500);
    }
}