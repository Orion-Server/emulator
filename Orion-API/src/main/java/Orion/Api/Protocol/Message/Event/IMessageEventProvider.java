package Orion.Api.Protocol.Message.Event;

import Orion.Api.Protocol.Message.IMessageEventHandler;
import gnu.trove.map.hash.THashMap;

public interface IMessageEventProvider {
    IMessageEventHandler getMessageEventByHeaderId(int headerId);

    THashMap<Integer, IMessageEventHandler> getMessageEventList();

    boolean containsMessageEvent(int headerId);

    void reloadMessageEvents();
}
