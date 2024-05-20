package Orion.Game.Room.Handler;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.Enums.RoomCategoryType;
import Orion.Api.Server.Game.Room.Enums.RoomTradeType;
import Orion.Api.Server.Game.Room.Handler.ICreateRoomHandler;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.IRoomManager;
import Orion.Api.Server.Game.Room.Utils.RoomEnvironmentVariables;
import Orion.Api.Storage.Repository.Room.IRoomRepository;
import Orion.Game.Room.Factory.RoomFactory;
import Orion.Protocol.Message.Composer.Navigator.CanCreateRoomComposer;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.concurrent.atomic.AtomicReference;

@Singleton
public class CreateRoomHandler implements ICreateRoomHandler {
    @Inject
    private IRoomRepository roomRepository;

    @Inject
    private IRoomManager roomManager;

    @Inject
    private RoomEnvironmentVariables roomEnvironmentVariables;

    @Inject
    private RoomFactory roomFactory;

    @Override
    public IRoom createRoom(
            IHabbo habbo,
            String name,
            String description,
            String modelName,
            RoomCategoryType categoryId,
            int maxUsers,
            RoomTradeType tradeType
    ) {
        if(categoryId == null || tradeType == null) return null;

        if(!this.roomManager.roomModelExists(modelName)) {
            System.out.println("model invalido");
            // TODO: Log error. Possible script kiddie.
            return null;
        }

        if(!this.roomManager.roomCategoryExists(categoryId.getId())) {
            System.out.println("categoria invalida");
            // TODO: Log error. Possible script kiddie.
            return null;
        }

        if(maxUsers > this.roomEnvironmentVariables.limitUsersPerRoom) {
            System.out.println("maximo de usuarios excedido");
            return null;
        }

        if(name.length() < this.roomEnvironmentVariables.minRoomNameLength || name.length() > this.roomEnvironmentVariables.maxRoomNameLength) {
            System.out.println("nome invalido");
            return null;
        }

        if(description.length() < this.roomEnvironmentVariables.minRoomDescriptionLength || description.length() > this.roomEnvironmentVariables.maxRoomDescriptionLength) {
            System.out.println("descricao invalida");
            return null;
        }

        final int roomLimit = this.roomEnvironmentVariables.userRoomsLimit; // TODO: Check user HC subscription

        if(habbo.getRooms().getOwnRooms().size() >= roomLimit) {
            habbo.getSession().send(new CanCreateRoomComposer(1, roomLimit));
            return null;
        }

        final AtomicReference<Integer> roomId = new AtomicReference<>(null);

        this.roomRepository.createRoom(result -> {
            if(result == null) return;

            roomId.set(result.getInt(1));
        }, habbo.getData().getId(), habbo.getData().getUsername(), name, description, modelName, categoryId.getId(), maxUsers, tradeType.get());

        if(roomId.get() == null) return null;

        final AtomicReference<IRoom> room = new AtomicReference<>(null);

        this.roomRepository.loadRoomById(result -> {
            if(result == null) return;

            room.set(this.roomFactory.create(result));
        }, roomId.get());

        if(room.get() == null) return null;

        habbo.getRooms().addOwnRoom(room.get());

        return room.get();
    }
}
