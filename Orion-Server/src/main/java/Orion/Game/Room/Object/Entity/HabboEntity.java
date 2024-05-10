package Orion.Game.Room.Object.Entity;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;
import Orion.Api.Server.Game.Util.Position;
import Orion.Writer.Room.Object.Entity.HabboEntityWriter;

public class HabboEntity implements IHabboEntity {
    private IHabbo habbo;

    private final int virtualId;

    private final IRoom room;

    private Position position;

    private int headRotation;
    private int bodyRotation;

    public HabboEntity(
            final int virtualId,
            final IHabbo habbo,
            final IRoom room
    ) {
        this.virtualId = virtualId;

        this.habbo = habbo;
        this.room = room;
        this.position = new Position(room.getModel().getData().getDoorX(), room.getModel().getData().getDoorY(), room.getModel().getDoorZ());

        this.headRotation = room.getModel().getData().getDoorDirection();
        this.bodyRotation = room.getModel().getData().getDoorDirection();
    }

    @Override
    public IHabbo getHabbo() {
        return this.habbo;
    }

    @Override
    public void setHabbo(IHabbo habbo) {
        this.habbo = habbo;
    }

    @Override
    public IRoom getRoom() {
        return this.room;
    }

    @Override
    public int getVirtualId() {
        return this.virtualId;
    }

    @Override
    public int getHeadRotation() {
        return this.headRotation;
    }

    @Override
    public int getBodyRotation() {
        return this.bodyRotation;
    }

    @Override
    public void setHeadRotation(int headRotation) {
        this.headRotation = headRotation;
    }

    @Override
    public void setBodyRotation(int bodyRotation) {
        this.bodyRotation = bodyRotation;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void write(IMessageComposer composer) {
        HabboEntityWriter.write(this, composer);
    }
}
