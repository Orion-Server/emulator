package Orion.Protocol.Message.Composer.Habbo.Messenger;

import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriend;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendsPage;
import Orion.Api.Server.Game.Habbo.Enums.HabboGender;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class MessengerFriendsComposer extends MessageComposer {
    public MessengerFriendsComposer(
            final int totalPages,
            final int pageIndex,
            final IMessengerFriendsPage page
    ) {
        super(ComposerHeaders.MessengerFriendsComposer);

        appendInt(totalPages);
        appendInt(pageIndex);
        appendInt(page.getFriends().size());

        for (final IMessengerFriend friend : page.getFriends().values()) {
            appendInt(friend.getId());
            appendString(friend.getUsername());
            appendInt(friend.getGender().equals(HabboGender.M) ? 0 : 1);
            appendBoolean(friend.getOnline() == 1);
            appendBoolean(friend.isInRoom());
            appendString(friend.getLookIfAvailable());
            appendInt(friend.getCategoryId());
            appendString(friend.getMotto());
            appendString("");
            appendString("");
            appendBoolean(false);
            appendBoolean(false);
            appendBoolean(false);
            appendShort(friend.getRelationType());
        }
    }
}
