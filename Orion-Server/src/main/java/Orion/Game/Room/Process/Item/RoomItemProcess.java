package Orion.Game.Room.Process.Item;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Api.Server.Task.IThreadManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class RoomItemProcess implements Runnable {
    private final IRoom room;

    private final Logger logger;

    private ScheduledFuture task;

    private boolean started = false;

    public RoomItemProcess(final IRoom room) {
        this.room = room;

        this.logger = LogManager.getLogger(STR."[Room Item Process #\{this.room.getData().getId()}]");
    }

    public void initialize(final IThreadManager threadManager) {
        if(this.started) return;

        this.task = threadManager.getRoomItemProcessingExecutor().scheduleAtFixedRate(this, 0, 500, TimeUnit.MILLISECONDS);
        this.started = true;
    }

    public void stop() {
        if(!this.started) return;

        this.task.cancel(true);
        this.started = false;
    }

    @Override
    public void run() {
        if(this.room.getEntitiesComponent().getHabboEntitiesCount() <= 0) return;

        this.processFloorItems();
    }

    private void processFloorItems() {
        // TODO: Improve this, maybe create a new property here to store the items that need to be ticked.
        for(final IRoomFloorItem floorItem : this.room.getItemsComponent().getFloorItems().values()) {
            if(floorItem.getInteraction().needsTick()) {
                floorItem.getInteraction().tick();
            }
        }
    }
}
