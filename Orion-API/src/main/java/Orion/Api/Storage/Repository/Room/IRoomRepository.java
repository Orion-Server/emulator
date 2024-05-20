package Orion.Api.Storage.Repository.Room;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IRoomRepository {
    void loadPublicRooms(IConnectionResultConsumer consumer, String isPublic, String isStaffPicked);

    void loadStaffPickedRooms(IConnectionResultConsumer consumer, String isStaffPicked);

    void loadFlatCategories(IConnectionResultConsumer consumer);

    void loadRoomModels(IConnectionResultConsumer consumer);

    void loadCustomRoomModel(IConnectionResultConsumer consumer, int roomId);

    void createRoom(IConnectionResultConsumer consumer, int ownerId, String ownerName, String name, String description, String modelName, int categoryId, int maxUsers, int tradeType);

    void loadRoomById(IConnectionResultConsumer consumer, int roomId);
}
