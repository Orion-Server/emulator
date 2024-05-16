package Orion.Api.Util;

import Orion.Api.Server.Game.Room.Object.Entity.Enum.RoomEntityStatus;
import gnu.trove.map.hash.THashMap;

public interface IStatusable {
    void setStatus(RoomEntityStatus status, final String value);

    void removeStatus(RoomEntityStatus status);

    boolean hasStatus(RoomEntityStatus status);

    String getStatus(RoomEntityStatus status);

    void clearStatuses();

    THashMap<RoomEntityStatus, String> getAllStatus();
}
