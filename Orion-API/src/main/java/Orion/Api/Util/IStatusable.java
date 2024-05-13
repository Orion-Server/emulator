package Orion.Api.Util;

import Orion.Api.Server.Game.Room.Object.Entity.Enum.RoomEntityStatus;

import java.util.concurrent.ConcurrentHashMap;

public interface IStatusable {
    void setStatus(RoomEntityStatus status, final String value);

    void removeStatus(RoomEntityStatus status);

    boolean hasStatus(RoomEntityStatus status);

    String getStatus(RoomEntityStatus status);

    void clearStatuses();

    ConcurrentHashMap<RoomEntityStatus, String> getAllStatus();
}
