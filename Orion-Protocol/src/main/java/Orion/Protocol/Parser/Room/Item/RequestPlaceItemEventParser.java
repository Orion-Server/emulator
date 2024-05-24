package Orion.Protocol.Parser.Room.Item;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

/**
 * 0: itemId
 * 1: X (floor items) or width position (wall items)
 * 2: Y (floor items) or length position (wall items)
 * 3: Rotation
 */
@Singleton
public class RequestPlaceItemEventParser implements IEventParser {
    public String data;

    public int itemId;

    public int x;
    public int y;
    public int rotation;

    public String wallPosition;

    @Override
    public int getId() {
        return EventHeaders.RequestPlaceItemEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.data = messageEvent.readString();

        final String[] dataParts = this.data.split(" ");

        if(dataParts.length <= 3) return;

        this.itemId = Integer.parseInt(dataParts[0]);

        if(dataParts[1].startsWith(":")) {
            this.wallPosition = String.join(" ", dataParts[1], dataParts[2], dataParts[3]);
            return;
        }

        this.x = Integer.parseInt(dataParts[1]);
        this.y = Integer.parseInt(dataParts[2]);
        this.rotation = Integer.parseInt(dataParts[3]);
    }

    public void dispose() {
        this.data = null;
        this.wallPosition = null;

        this.x = 0;
        this.y = 0;
        this.itemId = 0;
        this.rotation = 0;
    }
}
