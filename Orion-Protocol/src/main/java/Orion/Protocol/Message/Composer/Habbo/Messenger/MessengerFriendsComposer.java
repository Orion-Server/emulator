package Orion.Protocol.Message.Composer.Habbo.Messenger;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriend;
import Orion.Api.Server.Game.Habbo.Data.Messenger.IMessengerFriendsPage;
import Orion.Api.Server.Game.Habbo.Enums.HabboGender;
import Orion.Networking.Message.Composer;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class MessengerFriendsComposer extends Composer {
    private final int totalPages;
    private final int pageIndex;
    private final IMessengerFriendsPage page;

    public MessengerFriendsComposer(
            final int totalPages,
            final int pageIndex,
            final IMessengerFriendsPage page
    ) {
        this.totalPages = totalPages;
        this.pageIndex = pageIndex;
        this.page = page;
    }

    @Override
    public short getId() {
        return ComposerHeaders.MessengerFriendsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.totalPages);
        msg.appendInt(this.pageIndex);
        msg.appendInt(this.page.getFriends().size());

        for (final IMessengerFriend friend : this.page.getFriends().values()) {
            msg.appendInt(friend.getId());
            msg.appendString(friend.getUsername());
            msg.appendInt(friend.getGender().equals(HabboGender.M) ? 0 : 1);
            msg.appendBoolean(friend.getOnline() == 1);
            msg.appendBoolean(friend.isInRoom());
            msg.appendString(friend.getLookIfAvailable());
            msg.appendInt(friend.getCategoryId());
            msg.appendString(friend.getMotto());
            msg.appendString("");
            msg.appendString("");
            msg.appendBoolean(false);
            msg.appendBoolean(false);
            msg.appendBoolean(false);
            msg.appendShort(friend.getRelationType());
        }
    }
}
