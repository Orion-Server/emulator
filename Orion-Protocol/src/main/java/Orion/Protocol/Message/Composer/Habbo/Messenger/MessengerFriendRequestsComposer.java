package Orion.Protocol.Message.Composer.Habbo.Messenger;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendRequest;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.Composer;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class MessengerFriendRequestsComposer extends Composer {
    private final IHabbo habbo;

    public MessengerFriendRequestsComposer(final IHabbo habbo) {
        this.habbo = habbo;
    }

    @Override
    public short getId() {
        return ComposerHeaders.MessengerFriendRequestsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.habbo.getMessenger().getFriendRequests().size());
        msg.appendInt(this.habbo.getMessenger().getFriendRequests().size());

        for (final IMessengerFriendRequest friendRequest : this.habbo.getMessenger().getFriendRequests()) {
            msg.appendInt(friendRequest.getId());
            msg.appendString(friendRequest.getUsername());
            msg.appendString(friendRequest.getLook());
        }
    }
}
