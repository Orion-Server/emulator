package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Server.Game.Room.Data.Model.Enum.RoomTileState;
import Orion.Api.Server.Game.Room.Data.Model.IRoomTile;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomRelativeMapComposer extends MessageComposer {
    public RoomRelativeMapComposer(final IRoom room) {
        super(ComposerHeaders.RoomRelativeMapComposer);

        appendInt(room.getModel().getMapSizeX());
        appendInt(room.getModel().getMapSizeX() * room.getModel().getMapSizeY());

        for (short y = 0; y < room.getModel().getMapSizeY(); y++) {
            for (short x = 0; x < room.getModel().getMapSizeX(); x++) {
                if(room.getModel().getSquareState()[x][y] == RoomTileState.INVALID) {
                    appendShort(0x3F3F);
                    continue;
                }

                if(room.getModel().getData().getDoorX() == x && room.getModel().getData().getDoorY() == y) {
                    appendShort((short) (room.getModel().getSquareHeight()[x][y] * 256));
                    continue;
                }

                final IRoomTile tile = room.getMappingComponent().getTile(x, y);

                if(tile == null) {
                    appendShort((short) (room.getModel().getSquareHeight()[x][y] * 256));
                    continue;
                }

                appendShort((int) (tile.getStackHeight() * 256));
            }
        }
    }
}
