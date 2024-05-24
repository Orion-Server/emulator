package Orion.Protocol.Message.Event.Room.Item;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Item.Enum.FurnitureMovementError;
import Orion.Api.Server.Game.Util.Alert.MiddleAlertType;
import Orion.Protocol.Message.Composer.Alerts.MiddleAlertComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Room.Item.RequestPlaceItemEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestPlaceItemEvent implements IMessageEventHandler {
    @Inject
    private RequestPlaceItemEventParser parser;

    @Override
    public int getId() {
        return EventHeaders.RequestPlaceItemEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        final String data = event.readString();

        final String[] dataParts = data.split(" ");

        if(dataParts.length <= 3) return;

        final int itemId = Integer.parseInt(dataParts[0]);
        String wallPosition = "";

        if(dataParts[1].startsWith(":")) {
            wallPosition = String.join(" ", dataParts[1], dataParts[2], dataParts[3]);
        }

        final int x = Integer.parseInt(dataParts[1]);
        final int y = Integer.parseInt(dataParts[2]);
        final int rotation = Integer.parseInt(dataParts[3]);

        if(!session.getHabbo().isInRoom()) return;

        final IRoom room = session.getHabbo().getEntity().getRoom();

        if(room == null) return;

        if(!room.getRightsComponent().hasRights(session.getHabbo()) && !session.getHabbo().getPermission().hasAccountPermission("moverotate")) {
            session.send(new MiddleAlertComposer(MiddleAlertType.FURNITURE_PLACEMENT_ERROR, FurnitureMovementError.NO_RIGHTS));
            return;
        }

        System.out.println(data);
        System.out.println("-- floor data --");
        System.out.println(x);
        System.out.println(y);
        System.out.println(rotation);
        System.out.println("-- wall data --");
        System.out.println(wallPosition);
    }
}
