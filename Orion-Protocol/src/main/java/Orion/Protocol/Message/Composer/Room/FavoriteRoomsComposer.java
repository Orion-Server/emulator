package Orion.Protocol.Message.Composer.Room;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class FavoriteRoomsComposer extends Composer {
    private final IHabbo habbo;
    private final IEmulatorDatabaseSettings settings;

    public FavoriteRoomsComposer(final IHabbo habbo, final IEmulatorDatabaseSettings settings) {
        this.habbo = habbo;
        this.settings = settings;
    }

    @Override
    public short getId() {
        return ComposerHeaders.FavoriteRoomsComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.settings.getIntegerOrDefault("hotel.rooms.max.favorite", 30));
        msg.appendInt(0); // TODO
    }
}
