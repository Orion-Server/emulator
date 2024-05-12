package Orion.Game.Room.Process;

import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Process.IRoomProcess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoomProcess implements IRoomProcess {
    private final Logger logger;

    private final IRoom room;

    public RoomProcess(final IRoom room) {
        this.room = room;

        this.logger = LogManager.getLogger(STR."[Room Process #\{this.room.getData().getId()}]");
    }

    @Override
    public void initialize() {

    }

    @Override
    public void dispose() {

    }
}
