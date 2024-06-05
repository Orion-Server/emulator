package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Data.Model.Enum.RoomTileState;
import Orion.Api.Server.Game.Room.Data.Model.IRoomTile;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class RoomRelativeMapComposer extends Composer {
    private final IRoom room;

    public RoomRelativeMapComposer(final IRoom room) {
        this.room = room;
    }

    @Override
    public short getId() {
        return ComposerHeaders.RoomRelativeMapComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.room.getModel().getMapSizeX());
        msg.appendInt(this.room.getModel().getMapSizeX() * this.room.getModel().getMapSizeY());

        for (short y = 0; y < this.room.getModel().getMapSizeY(); y++) {
            for (short x = 0; x < this.room.getModel().getMapSizeX(); x++) {
                if(this.room.getModel().getSquareState()[x][y] == RoomTileState.INVALID) {
                    msg.appendShort(0x3F3F);
                    continue;
                }

                if(this.room.getModel().getData().getDoorX() == x && this.room.getModel().getData().getDoorY() == y) {
                    msg.appendShort((short) (this.room.getModel().getSquareHeight()[x][y] * 256));
                    continue;
                }

                final IRoomTile tile = this.room.getMappingComponent().getTile(x, y);

                if(tile == null) {
                    msg.appendShort((short) (this.room.getModel().getSquareHeight()[x][y] * 256));
                    continue;
                }

                msg.appendShort((int) (tile.getStackHeight() * 256));
            }
        }
    }
}
