package Orion.Game.Room.Process;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Room.Process.IRoomProcess;
import Orion.Api.Server.Task.IThreadManager;
import Orion.Game.Room.Process.Entity.HabboEntityProcess;
import Orion.Game.Room.Process.Item.RoomItemProcess;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class RoomProcess implements IRoomProcess {
    @Inject
    private IThreadManager threadManager;

    private final Logger logger;

    private final IRoom room;

    private ScheduledFuture task;

    private boolean started = false;
    private boolean processing = false;

    private final RoomItemProcess roomItemProcess;

    private final HabboEntityProcess habboEntityProcess;

    public RoomProcess(final IRoom room) {
        this.room = room;
        this.logger = LogManager.getLogger(STR."[Room Process #\{this.room.getData().getId()}]");

        this.habboEntityProcess = new HabboEntityProcess(room);
        this.roomItemProcess = new RoomItemProcess(room);
    }

    @Override
    public void initialize() {
        if(this.started) return;

        this.task = this.threadManager.getRoomProcessingExecutor().scheduleAtFixedRate(this, 500, 500, TimeUnit.MILLISECONDS);

        this.roomItemProcess.initialize(this.threadManager);

        this.started = true;
    }

    @Override
    public void onEntityRemoved(IRoomEntity entity) {
        this.habboEntityProcess.onEntityRemoved(entity);
    }

    @Override
    public void run() {
        if(!this.started || this.processing) return;

        this.processing = true;

        try {
            this.habboEntityProcess.process();
        } catch (final Exception e) {
            this.logger.error(STR."Error processing room [\{this.room.getData().getId()}]", e);
        }

        this.processing = false;
    }

    @Override
    public void dispose() {
        this.task.cancel(true);

        this.roomItemProcess.stop();
    }
}
