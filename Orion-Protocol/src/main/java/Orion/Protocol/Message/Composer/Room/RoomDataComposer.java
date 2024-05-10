package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomDataComposer extends MessageComposer {
    public RoomDataComposer(
            final IHabbo habbo,
            final IRoom room,
            final boolean roomForward,
            final boolean enterRoom
    ) {
        super(ComposerHeaders.RoomDataComposer);

        appendBoolean(enterRoom);

        room.write(this);

        appendBoolean(roomForward);
        appendBoolean(room.getData().isStaffPicked());
        appendBoolean(false); // TODO: Check if habbo is member of room guild
        appendBoolean(false); // TODO: is muted

        appendInt(room.getData().getWhoCanMute());
        appendInt(room.getData().getWhoCanKick());
        appendInt(room.getData().getWhoCanBan());

        appendBoolean(false); // TODO: Permissions: mute all button

        appendInt(room.getData().getChatMode());
        appendInt(room.getData().getChatWeight());
        appendInt(room.getData().getChatSpeed());
        appendInt(room.getData().getChatDistance());
        appendInt(room.getData().getChatProtection());
    }
}
