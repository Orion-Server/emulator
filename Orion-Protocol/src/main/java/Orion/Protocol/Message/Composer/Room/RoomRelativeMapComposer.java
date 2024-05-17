package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Server.Game.Room.Data.Model.Enum.ModelTileState;
import Orion.Api.Server.Game.Room.Data.Model.IRoomTile;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomRelativeMapComposer extends MessageComposer {
    public RoomRelativeMapComposer(final IRoom room) {
        super(ComposerHeaders.RoomRelativeMapComposer);

        appendInt(room.getModel().getMapSize() / room.getModel().getMapSizeY());
        appendInt(room.getModel().getMapSize());

        for (short y = 0; y < room.getModel().getMapSizeY(); y++) {
            for (short x = 0; x < room.getModel().getMapSizeX(); x++) {
                final IRoomTile tile = room.getMappingComponent().getTile(x, y);

                if(tile == null) {
                    appendShort(room.getModel().getSquareHeight()[x][y] * 256);
                    return;
                }

                if(room.getModel().getSquareState()[x][y] == ModelTileState.INVALID) {
                    appendShort(0x3F3F);
                    continue;
                }

                if(room.getModel().getData().getDoorX() == x && room.getModel().getData().getDoorY() == y) {
                    appendShort(0);
                    continue;
                }

                appendShort((int) (tile.getStackHeight() * 256));
            }
        }
    }
}
