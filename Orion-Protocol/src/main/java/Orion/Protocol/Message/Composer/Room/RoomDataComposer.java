package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.Composer;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomDataComposer extends Composer {
    private final IHabbo habbo;
    private final IRoom room;
    private final boolean roomForward;
    private final boolean enterRoom;

    public RoomDataComposer(
            final IHabbo habbo,
            final IRoom room,
            final boolean roomForward,
            final boolean enterRoom
    ) {
        this.habbo = habbo;
        this.room = room;
        this.roomForward = roomForward;
        this.enterRoom = enterRoom;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomDataComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendBoolean(this.enterRoom);

        room.write(msg);

        msg.appendBoolean(this.roomForward);
        msg.appendBoolean(this.room.getData().isStaffPicked());
        msg.appendBoolean(false); // TODO: Check if habbo is member of room guild
        msg.appendBoolean(this.room.isMuted());

        msg.appendInt(this.room.getData().getWhoCanMute());
        msg.appendInt(this.room.getData().getWhoCanKick());
        msg.appendInt(this.room.getData().getWhoCanBan());

        msg.appendBoolean(this.room.getRightsComponent().hasRights(habbo));

        msg.appendInt(this.room.getData().getChatMode());
        msg.appendInt(this.room.getData().getChatWeight());
        msg.appendInt(this.room.getData().getChatSpeed());
        msg.appendInt(this.room.getData().getChatDistance());
        msg.appendInt(this.room.getData().getChatProtection());
    }
}
