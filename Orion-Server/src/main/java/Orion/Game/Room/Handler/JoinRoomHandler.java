package Orion.Game.Room.Handler;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.Enums.RoomAccessError;
import Orion.Api.Server.Game.Room.Enums.RoomAccessState;
import Orion.Api.Server.Game.Room.Enums.RoomRightLevel;
import Orion.Api.Server.Game.Room.Handler.IJoinRoomHandler;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.Object.Entity.Enum.RoomEntityStatus;
import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;
import Orion.Api.Server.Game.Util.Alert.GenericErrorType;
import Orion.Game.Room.Object.Entity.Factory.HabboEntityFactory;
import Orion.Protocol.Message.Composer.Alerts.GenericErrorComposer;
import Orion.Protocol.Message.Composer.HotelView.GoToHotelViewComposer;
import Orion.Protocol.Message.Composer.Room.Access.AddHabboToDoorbellComposer;
import Orion.Protocol.Message.Composer.Room.Access.RequestRoomAccessComposer;
import Orion.Protocol.Message.Composer.Room.Access.RoomAccessDeniedComposer;
import Orion.Protocol.Message.Composer.Room.Access.RoomAccessErrorComposer;
import Orion.Protocol.Message.Composer.Room.Entities.RoomEntitiesComposer;
import Orion.Protocol.Message.Composer.Room.Entities.RoomEntityStatusComposer;
import Orion.Protocol.Message.Composer.Room.Object.RoomFloorObjectsComposer;
import Orion.Protocol.Message.Composer.Room.Object.RoomWallObjectsComposer;
import Orion.Protocol.Message.Composer.Room.Painting.RoomPaintComposer;
import Orion.Protocol.Message.Composer.Room.Rights.RoomOwnerComposer;
import Orion.Protocol.Message.Composer.Room.Rights.RoomRightsComposer;
import Orion.Protocol.Message.Composer.Room.Rights.RoomRightsListComposer;
import Orion.Protocol.Message.Composer.Room.RoomDataComposer;
import Orion.Protocol.Message.Composer.Room.RoomPaneComposer;
import Orion.Protocol.Message.Composer.Room.RoomPromotionComposer;
import Orion.Protocol.Message.Composer.Room.RoomThicknessComposer;
import Orion.Protocol.Message.Composer.Room.Score.RoomScoreComposer;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class JoinRoomHandler implements IJoinRoomHandler {
    private final String[] roomPrivilegesPermissions = new String[]{"anyroomowner", "anyroomrights"};

    @Inject
    private HabboEntityFactory habboEntityFactory;

    @Override
    public void prepareRoom(IRoom room, IHabbo habbo, String password) {
        if(room == null) return;

        if(habbo.getEntity() != null && habbo.getEntity().getRoom() != null) {
            habbo.getEntity().getRoom().getEntitiesComponent().removeEntity(habbo.getEntity());
            habbo.setEntity(null);
        }

        if(habbo.hasStatus("room_doorbell")) {
            this.treatHabboWithDoorbell(habbo);
        }

        // TODO: Check teleport


        final boolean habboHasHighRoomPrivileges = room.habboIsOwner(habbo)
                || habbo.getPermission().hasAccountPermissions(this.roomPrivilegesPermissions);

        if(room.getEntitiesComponent().getHabboEntities().size() >= room.getData().getMaxUsers() && !habboHasHighRoomPrivileges) {
            habbo.getSession().send(new GoToHotelViewComposer());
            return;
        }

        if(room.getBansComponent().habboIsBanned(habbo) && !habboHasHighRoomPrivileges) {
            habbo.getSession().send(new RoomAccessErrorComposer(RoomAccessError.ROOM_BANNED));
            return;
        }

        // TODO: Check queue

        // TODO: Check guilds

        if(room.getData().getAccessState() == RoomAccessState.OPEN || habboHasHighRoomPrivileges) {
            this.finalizeEntryPackets(room, habbo);
            return;
        }

        switch (room.getData().getAccessState()) {
            case PASSWORD:
                this.treatPasswordRoom(room, habbo, password);
                break;
            case LOCKED:
                this.treatLockedRoom(room, habbo);
                break;
            case INVISIBLE:
                this.treatInvisibleRoom(room, habbo);
                break;
            default:
                habbo.getSession().send(new GoToHotelViewComposer());
        }
    }

    private void treatHabboWithDoorbell(IHabbo habbo) {
        try {
            final IRoom doorbellRoom = (IRoom) habbo.getStatus("room_doorbell");

            if(doorbellRoom == null) return;

            for (final IHabboEntity habboWithRight : doorbellRoom.getEntitiesComponent().getHabbosWithRights()) {
                habboWithRight.getHabbo().getSession().send(new RequestRoomAccessComposer(habbo.getData().getUsername()));
            }
        } catch (Exception e) {
            habbo.getSession().send(new GoToHotelViewComposer());
        }

        habbo.removeStatus("room_doorbell");
    }

    private void treatInvisibleRoom(IRoom room, IHabbo habbo) {
        if(room.getData().getOwnerId() == habbo.getData().getId() || room.getRightsComponent().hasRights(habbo)) {
            this.finalizeEntryPackets(room, habbo);
            return;
        }

        habbo.getSession().send(new RoomAccessErrorComposer(RoomAccessError.CANT_ENTER));
    }

    private void treatPasswordRoom(IRoom room, IHabbo habbo, String password) {
        if(room.getData().getPassword().equals(password)) {
            this.finalizeEntryPackets(room, habbo);
            return;
        }

        habbo.getSession().send(new GenericErrorComposer(GenericErrorType.WRONG_PASSWORD));
        habbo.getSession().send(new GoToHotelViewComposer());
    }

    private void treatLockedRoom(IRoom room, IHabbo habbo) {
        final List<IHabboEntity> habbosWithRights = room.getEntitiesComponent().getHabbosWithRights();

        if(habbosWithRights.isEmpty()) {
            habbo.getSession().send(new RoomAccessDeniedComposer(habbo.getData().getUsername()));
            habbo.getSession().send(new GoToHotelViewComposer());

            return;
        }

        for (final IHabboEntity habboWithRight : habbosWithRights) {
            habboWithRight.getHabbo().getSession().send(new AddHabboToDoorbellComposer(habbo.getData().getUsername()));
        }

        habbo.addStatus("room_doorbell", room);
        habbo.getSession().send(new AddHabboToDoorbellComposer(""));
    }

    @Override
    public void finalizeEntryPackets(IRoom room, IHabbo habbo) {
        habbo.removeStatus("room_doorbell");
        habbo.getSession().send(new RequestRoomAccessComposer(""));

        habboEntityFactory.create(room, habbo);

        // TODO: update messenger

        final List<IMessageComposer> composers = new ArrayList<>();

        if(!room.getData().getPaperWall().equals("0.0")) {
            composers.add(new RoomPaintComposer("wallpaper", room.getData().getPaperWall()));
        }

        if(!room.getData().getPaperFloor().equals("0.0")) {
            composers.add(new RoomPaintComposer("floor", room.getData().getPaperFloor()));
        }

        composers.add(new RoomPaintComposer("wallpaper", room.getData().getPaperLandscape()));

        this.resolveRoomRightsForHabbo(composers, room, habbo);

        composers.add(new RoomScoreComposer(room.getData().getScore(), !room.getVotesComponent().habboHasVote(habbo)));

        // TODO: send room promotion

        composers.add(new RoomPromotionComposer());

        habbo.getSession().send(composers);

        composers.clear();
    }

    private void resolveRoomRightsForHabbo(List<IMessageComposer> composers, IRoom room, IHabbo habbo) {
        RoomRightLevel flatCtrl = RoomRightLevel.None;

        // TODO: check renting space

        if(room.habboIsOwner(habbo) || habbo.getPermission().hasAccountPermission(this.roomPrivilegesPermissions[0])) {
            flatCtrl = RoomRightLevel.Moderator;

            composers.add(new RoomRightsComposer(room.getRightsComponent().getRightLevelFor(habbo)));
            composers.add(new RoomRightsListComposer(room));
        }

        if(room.getRightsComponent().hasRights(habbo) && room.getData().getGuildId() == 0) {
            flatCtrl = RoomRightLevel.Rights;
        }

        // TODO: Check group rights

        habbo.getEntity().setStatus(RoomEntityStatus.FLATCTRL, String.valueOf(flatCtrl.ordinal()));
    }

    @Override
    public void finalizeRoomEnter(IRoom room, IHabbo habbo) {
        if(!habbo.isInRoom()) {
            habbo.setEntity(null);
        }

        if(habbo.getEntity().getRoom().getData().getId() != room.getData().getId()) {
            habbo.getEntity().getRoom().getEntitiesComponent().removeEntity(habbo.getEntity());
            habbo.setEntity(null);
        }

        if(habbo.getEntity() == null) {
            habbo.getSession().send(new GoToHotelViewComposer());
            return;
        }

        final List<IMessageComposer> composers = new ArrayList<>();

        if(room.habboIsOwner(habbo)) {
            composers.add(new RoomOwnerComposer());
        }

        // TODO: Send handItem

        room.initialize();

        room.broadcastMessages(
                new RoomEntitiesComposer(habbo.getEntity()),
                new RoomEntityStatusComposer(habbo.getEntity())
        );

        composers.add(new RoomEntitiesComposer(room.getEntitiesComponent().getRoomEntities()));
        composers.add(new RoomEntityStatusComposer(room.getEntitiesComponent().getRoomEntities()));

        // TODO: Send room guild

        // TODO: Send room bots

        composers.add(new RoomPaneComposer(room, room.habboIsOwner(habbo)));
        composers.add(new RoomThicknessComposer(room));

        composers.add(new RoomDataComposer(habbo, room, false, true));

        composers.add(new RoomWallObjectsComposer(room));
        composers.add(new RoomFloorObjectsComposer(room));

        // TODO: Send pets
        // TODO: Send flood counter and muted
        // TODO: Send pool

        habbo.getSession().send(composers);

        composers.clear();
    }
}
