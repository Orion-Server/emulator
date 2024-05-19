package Orion.Api.Util;

public interface IAttributable {
    void setAttribute(String key, Object value);

    Object getAttribute(String key);

    boolean hasAttribute(String key);

    void removeAttribute(String key);
}
