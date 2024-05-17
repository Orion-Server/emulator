package Orion.Game.Room.Process.Entities;

import Orion.Api.Server.Game.Room.Data.Model.IRoomTile;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Entity.Enum.RoomEntityStatus;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;
import Orion.Api.Server.Game.Util.Position;
import Orion.Protocol.Message.Composer.Room.Entities.RoomEntityStatusComposer;

import java.util.ArrayList;
import java.util.List;

public class HabboEntityProcess {
    private final IRoom room;

    private final List<IRoomEntity> entitiesToUpdate;

    public HabboEntityProcess(final IRoom room) {
        this.room = room;

        this.entitiesToUpdate = new ArrayList<>();
    }

    public void onEntityRemoved(final IRoomEntity entity) {
        if(entity instanceof IHabboEntity) {
            this.entitiesToUpdate.remove(entity);
        }
    }

    public void process() {
        for(final IHabboEntity entity : this.room.getEntitiesComponent().getHabboEntities()) {
            if(entity.hasStatus(RoomEntityStatus.MOVE)) {
                entity.removeStatus(RoomEntityStatus.MOVE);
                entity.removeStatus(RoomEntityStatus.GESTURE);

                this.entitiesToUpdate.add(entity);
            }

            if(!entity.getWalkComponent().getWalkingPath().isEmpty()) {
                entity.getWalkComponent().setProcessingPath(entity.getWalkComponent().getWalkingPath());
                entity.getWalkComponent().clearWalkingPath();
            }

            if(entity.isWalking()) {
                this.processHabboEntityWalk(entity);
            }
        }

        if(this.entitiesToUpdate.isEmpty()) return;

        this.room.broadcastMessage(new RoomEntityStatusComposer(this.entitiesToUpdate));

        for(final IRoomEntity entity : this.entitiesToUpdate) {
            if(entity.getNextPosition() == null) continue;

            final Position nextPosition = entity.getNextPosition().copy();
            IRoomTile tile = this.room.getMappingComponent().getTile(nextPosition.getX(), nextPosition.getY());

            if(tile == null) continue;

            entity.setPosition(nextPosition);
            entity.setNextPosition(null);

            tile.onEntityEnter(entity);
        }

        this.entitiesToUpdate.clear();
    }

    private void processHabboEntityWalk(final IHabboEntity entity) {
        final Position nextPosition = entity.getWalkComponent().getProcessingPath().getFirst();
        final IRoomTile tile = this.room.getMappingComponent().getTile(entity.getPosition().getX(), entity.getPosition().getY());

        if(tile == null) return;

        entity.setBodyRotation(Position.calculateRotation(entity.getPosition(), nextPosition, false));
        entity.setHeadRotation(entity.getBodyRotation());

        entity.setStatus(RoomEntityStatus.MOVE, STR."\{nextPosition.getX()},\{nextPosition.getY()},\{nextPosition.getZ()}");

        entity.removeStatus(RoomEntityStatus.LAY);
        entity.removeStatus(RoomEntityStatus.SIT);

        entity.setNextPosition(nextPosition.copy());

        entity.getWalkComponent().getProcessingPath().removeFirst();

        this.entitiesToUpdate.add(entity);

        if(!entity.isWalking()) {
            entity.getWalkComponent().clearWalkingPath();
            entity.getWalkComponent().clearProcessingPath();
        }

        tile.onEntityLeave(entity);
    }
}
