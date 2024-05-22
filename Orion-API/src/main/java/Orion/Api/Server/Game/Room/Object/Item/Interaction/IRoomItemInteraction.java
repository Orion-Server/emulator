package Orion.Api.Server.Game.Room.Object.Item.Interaction;

import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;

public interface IRoomItemInteraction {
    boolean needsTick();

    void setTickCounter(double tickCounter);

    void finalizeTicks();

    void tick();

    void onTick();

    void onTickCompleted();

    void onPlaced();

    void onPickup();

    void onInteract(IRoomEntity entity, int requestData);

    void onEntityEnter(IRoomEntity entity);

    void onEntityLeave(IRoomEntity entity);
}
