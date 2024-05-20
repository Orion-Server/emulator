package Orion.Api.Server.Game.Room.Object.Item.Interaction;

import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;

public interface IRoomItemInteraction {
    void tick();

    void onBeforeTick();

    void onAfterTick();

    void onPlaced();

    void onPickup();

    void onInteract(IRoomEntity entity);

    void onEntityEnter(IRoomEntity entity);

    void onEntityLeave(IRoomEntity entity);
}
