package Orion.Protocol.Message.Composer.Habbo.Messenger;

import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendRequest;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class MessengerFriendRequestsComposer extends MessageComposer {
    public MessengerFriendRequestsComposer(final IHabbo habbo) {
        super(ComposerHeaders.MessengerFriendRequestsComposer);

        appendInt(habbo.getMessenger().getFriendRequests().size());
        appendInt(habbo.getMessenger().getFriendRequests().size());

        for (final IMessengerFriendRequest friendRequest : habbo.getMessenger().getFriendRequests()) {
            appendInt(friendRequest.getId());
            appendString(friendRequest.getUsername());
            appendString(friendRequest.getLook());
        }
    }
}
