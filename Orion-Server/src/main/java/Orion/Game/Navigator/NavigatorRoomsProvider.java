package Orion.Game.Navigator;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Navigator.INavigatorManager;
import Orion.Api.Server.Game.Navigator.INavigatorRoomsProvider;
import Orion.Api.Server.Game.Room.Data.IRoomCategory;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.IRoomManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class NavigatorRoomsProvider implements INavigatorRoomsProvider {
    @Inject
    private INavigatorManager navigatorManager;

    @Inject
    private IRoomManager roomManager;

    @Override
    public List<IRoom> getRoomsForCategory(String category, IHabbo habbo) {
        final List<IRoom> rooms = new ArrayList<>();

        switch (category) {
            case "official-root" -> rooms.addAll(this.getPublicRooms());
            case "my" -> rooms.addAll(habbo.getRooms().getOwnRooms().values());
            case "favorites" -> rooms.addAll(habbo.getRooms().getFavoriteRooms().values());
            case "history_freq" -> rooms.addAll(habbo.getRooms().getRoomHistory().values());
            case "my_groups" -> new ArrayList<>(); // TODO: Implement guild rooms
            case "with_rights" -> rooms.addAll(habbo.getRooms().getRoomsWithRights().values());
            case "popular" -> rooms.addAll(this.getPopularRooms());
            case "categories" -> rooms.addAll(this.getPromotedRooms());
            case "with_friends" -> new ArrayList<>(); // TODO: Implement friends
            case "highest_score" -> rooms.addAll(this.getTopRatedRooms());
            default -> new ArrayList<>();
        };

        return rooms;
    }

    @Override
    public List<IRoom> getPublicRooms() {
        final List<IRoom> publicRooms = new ArrayList<>();

        this.roomManager.getLoadedRooms().forEach((_, room) -> {
            if(!room.getData().isPublic()) return;

            publicRooms.add(room);
        });

        // TODO: Sort

        return publicRooms;
    }

    @Override
    public List<IRoom> getPopularRooms() {
        final List<IRoom> popularRooms = new ArrayList<>();

        this.roomManager.getLoadedRooms().forEach((_, room) -> {
            if(!room.isFullyLoaded()) return; // TODO: Check current users in room and if it's public

            popularRooms.add(room);
        });

        // TODO: Sort

        return popularRooms;
    }

    @Override
    public List<IRoom> getPromotedRooms() {
        final List<IRoom> promotedRooms = new ArrayList<>();

        this.roomManager.getLoadedRooms().forEach((_, room) -> {
            if (!room.getData().isPromoted()) return;

            promotedRooms.add(room);
        });

        return promotedRooms;
    }

    @Override
    public List<IRoom> getTopRatedRooms() {
        // TODO: Maybe cache this or improve this solution
        return this.roomManager.getLoadedRooms().values().stream()
                //.filter(IRoom::isFullyLoaded)
                .sorted(Comparator.comparing(room -> room.getData().getScore()))
                .limit(20)
                .collect(Collectors.toList());
    }

    @Override
    public HashMap<IRoomCategory, List<IRoom>> getRoomsFromCategories(IHabbo habbo) {
        final HashMap<IRoomCategory, List<IRoom>> roomsByCategory = new HashMap<>();

        for (final IRoomCategory category : this.roomManager.getRoomCategories().values()) {
            final List<IRoom> rooms = this.roomManager.getLoadedRoomsBy(room -> room.getData().getCategoryId() == category.getId());

            // TODO: Should I skip the category or just display it empty? Currently skipping
            if (rooms.isEmpty()) continue;

            roomsByCategory.put(category, rooms);
        }

        return roomsByCategory;
    }
}
