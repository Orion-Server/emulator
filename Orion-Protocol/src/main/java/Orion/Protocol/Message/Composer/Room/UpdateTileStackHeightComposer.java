package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Server.Game.Room.Data.Model.IRoomTile;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

import java.util.List;

public class UpdateTileStackHeightComposer extends MessageComposer {
    public UpdateTileStackHeightComposer(final List<IRoomTile> tilesToUpdate) {
        super(ComposerHeaders.UpdateTileStackHeightComposer);

        appendInt(tilesToUpdate.size());

        for (final IRoomTile tile : tilesToUpdate) {
            appendByte(tile.getPosition().getX());
            appendByte(tile.getPosition().getY());
            appendShort((short) tile.getStackHeight());
        }
    }
}
