package Orion.Game.Room.Object.Item.Interaction;

import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Room.Object.Item.Interaction.IRoomItemInteraction;

public class RoomItemInteraction implements IRoomItemInteraction {
    protected int tickCounter = -1;

    @Override
    public boolean needsTick() {
        return this.tickCounter > 0;
    }

    @Override
    public void setTickCounter(double tickCounter) {
        long realTime = Math.round(tickCounter * 1000F / 500F);

        if (realTime < 1) {
            realTime = 1; //0.5s
        }

        this.tickCounter = (int) realTime;
    }

    @Override
    public void finalizeTicks() {
        this.tickCounter = -1;
    }

    @Override
    public void tick() {
        this.onTick();

        if(this.tickCounter > 0) {
            this.tickCounter--;
        }

        if(this.tickCounter == 0) {
            this.finalizeTicks();
            this.onTickCompleted();
        }
    }

    @Override
    public void onTickCompleted() {
        // Override this method to add custom logic
    }

    @Override
    public void onTick() {
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
    public void onInteract(IRoomEntity entity, int requestData) {
        // Override this method to add custom logic
    }

    @Override
    public void onEntityEnter(IRoomEntity entity) {
        // Override this method to add custom logic
    }

    @Override
    public void onEntityLeave(IRoomEntity entity) {
        // Override this method to add custom logic
    }
}
