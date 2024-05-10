package Orion.Api.Server.Game.Habbo.Compositions;

import java.util.concurrent.ConcurrentHashMap;

public interface IStatusable {
    void addStatus(String condition, Object value);

    void removeStatus(String condition);

    void clearStatus();

    boolean hasStatus(String condition);

    Object getStatus(String condition);

    ConcurrentHashMap<String, Object> getStatus();
}
