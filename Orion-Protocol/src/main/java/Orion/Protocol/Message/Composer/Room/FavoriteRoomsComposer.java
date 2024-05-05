package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class FavoriteRoomsComposer extends MessageComposer {
    public FavoriteRoomsComposer(final IHabbo habbo, final IEmulatorDatabaseSettings settings) {
        super(ComposerHeaders.FavoriteRoomsComposer);

        appendInt(settings.getIntegerOrDefault("hotel.rooms.max.favorite", 30));
        appendInt(0); // TODO
    }
}
