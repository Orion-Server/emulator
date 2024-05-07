package Orion.Game.Navigator.Data;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Navigator.Data.INavigatorFilterType;
import Orion.Api.Server.Game.Navigator.Data.INavigatorResultCategory;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayMode;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayOrder;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorLayoutDisplay;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorListAction;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Writer.Habbo.Navigator.NavigatorResultCategoryWriter;

import java.util.List;

public record NavigatorResultCategory(
        int order,
        String category,
        String search,
        NavigatorListAction action,
        NavigatorDisplayMode mode,
        NavigatorLayoutDisplay hidden,
        List<IRoom> rooms,
        boolean filter,
        boolean showInvisible,
        NavigatorDisplayOrder displayOrder,
        int categoryOrder
) implements INavigatorResultCategory
{
    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public String getSearch() {
        return this.search;
    }

    @Override
    public NavigatorListAction getAction() {
        return this.action;
    }

    @Override
    public NavigatorLayoutDisplay getHidden() {
        return this.hidden;
    }

    @Override
    public NavigatorDisplayMode getMode() {
        return this.mode;
    }

    @Override
    public boolean canShowInvisibleRooms() {
        return this.showInvisible;
    }

    @Override
    public int getCategoryOrder() {
        return this.categoryOrder;
    }

    @Override
    public void write(final IMessageComposer composer) {
        NavigatorResultCategoryWriter.write(composer, this);
    }

    public boolean filterRooms(INavigatorFilterType type, String search) {
        if(search.isEmpty()) return true;

        this.rooms.removeIf(room -> switch (type.getKey()) {
            case "anything" -> false;
            case "roomname" -> !room.getData().getName().toLowerCase().contains(search.toLowerCase());
            case "tag" -> !room.getData().getTags().contains(search);
            case "owner" -> !room.getData().getOwnerName().equalsIgnoreCase(search);
            case "desc" -> !room.getData().getDescription().toLowerCase().contains(search.toLowerCase());
            // TODO: "promo" and "group" cases
            default -> false;
        });

        return !this.rooms.isEmpty();
    }

    @Override
    public List<IRoom> getRooms() {
        return this.rooms;
    }

    @Override
    public int compareTo(INavigatorResultCategory o) {
        if (this.displayOrder != NavigatorDisplayOrder.ACTIVITY) {
            return this.categoryOrder - o.getCategoryOrder();
        }

        if (this.category.equalsIgnoreCase("popular")) {
            return -1;
        }

        return this.rooms.size() - o.getRooms().size();
    }
}
