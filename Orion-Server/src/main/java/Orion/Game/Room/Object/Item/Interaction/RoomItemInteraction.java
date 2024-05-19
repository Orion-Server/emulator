package Orion.Game.Room.Object.Item.Interaction;

import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Room.Object.Item.Interaction.IRoomItemInteraction;

public class RoomItemInteraction implements IRoomItemInteraction {
    @Override
    public void tick() {
        // Override this method to add custom logic
    }

    @Override
    public void onBeforeTick() {
        // Override this method to add custom logic
    }

    @Override
    public void onAfterTick() {
        // Override this method to add custom logic
    }

    @Override
    public void onPlaced() {
        // Override this method to add custom logic
    }

    @Override
    public void onPickup() {
        // Override this method to add custom logic
    }

    @Override
    public void onInteract(IRoomEntity entity) {
        // Override this method to add custom logic
    }

    @Override
    public void onEntityEnter() {
        // Override this method to add custom logic
    }
}
