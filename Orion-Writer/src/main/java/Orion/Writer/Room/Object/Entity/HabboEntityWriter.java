package Orion.Writer.Room.Object.Entity;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;

public abstract class HabboEntityWriter {
    public static void write(
            final IHabboEntity entity,
            final IMessageComposer composer
    ) {
        composer.appendInt(entity.getHabbo().getData().getId());
        composer.appendString(entity.getHabbo().getData().getUsername());
        composer.appendString(entity.getHabbo().getData().getMotto());
        composer.appendString(entity.getHabbo().getData().getLook());
        composer.appendInt(entity.getVirtualId());
        composer.appendInt(entity.getPosition().getX());
        composer.appendInt(entity.getPosition().getY());
        composer.appendString(String.valueOf(entity.getPosition().getZ()));
        composer.appendInt(entity.getBodyRotation());
        composer.appendInt(1);
        composer.appendString(entity.getHabbo().getData().getGender().toUpperCase());
        composer.appendInt(-1);
        composer.appendInt(-1);
        composer.appendString("");
        composer.appendString("");
        composer.appendInt(entity.getHabbo().getSettings().getAchievementScore());
        composer.appendBoolean(true);
    }
}
