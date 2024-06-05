package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Data.Model.IRoomTile;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.List;

public class UpdateTileStackHeightComposer extends Composer {
    private final List<IRoomTile> tilesToUpdate;

    public UpdateTileStackHeightComposer(final List<IRoomTile> tilesToUpdate) {
        this.tilesToUpdate = tilesToUpdate;
    }

    @Override
    public short getId() {
        return ComposerHeaders.UpdateTileStackHeightComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.tilesToUpdate.size());

        for (final IRoomTile tile : this.tilesToUpdate) {
            msg.appendByte(tile.getPosition().getX());
            msg.appendByte(tile.getPosition().getY());
            msg.appendShort((short) tile.getStackHeight());
        }
    }
}
