package Orion.Writer.Habbo.Navigator;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Navigator.Data.INavigatorResultCategory;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorLayoutDisplay;
import Orion.Api.Server.Game.Room.Enums.RoomAccessState;
import Orion.Api.Server.Game.Room.IRoom;

public abstract class NavigatorResultCategoryWriter {
    public static void write(
            final IMessageComposer composer,
            final INavigatorResultCategory category
    ) {
        composer.appendString(category.getCategory());
        composer.appendString(category.getSearch());
        composer.appendInt(category.getAction().get());
        composer.appendBoolean(category.getHidden().equals(NavigatorLayoutDisplay.COLLAPSED));
        composer.appendInt(category.getMode().get());

        synchronized (category.getRooms()) {
            if (!category.canShowInvisibleRooms()) {
                category.getRooms().removeIf(room -> room.getData().getAccessState().equals(RoomAccessState.INVISIBLE));
            }

            composer.appendInt(category.getRooms().size());
//                Collections.sort(category.rooms);
            for (final IRoom room : category.getRooms()) {
                room.write(composer);
            }
        }
    }
}
