package Orion.Game.Room.Object.Item.Interaction.Floor;

import Orion.Api.Server.Game.Room.Object.Entity.Enum.RoomEntityStatus;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Api.Server.Game.Util.Position;
import Orion.Game.Room.Object.Item.Composition.InteractionName;
import Orion.Game.Room.Object.Item.Interaction.RoomItemInteraction;

@InteractionName("vendingmachine")
public class VendingMachineFloorItem extends RoomItemInteraction {
    private final IRoomFloorItem item;

    private int state = -1;

    private IRoomEntity interactedEntity;

    public VendingMachineFloorItem(IRoomFloorItem item) {
        this.item = item;
    }

    @Override
    public void onInteract(IRoomEntity entity, int requestData) {
        if(!(entity instanceof IHabboEntity habbo)) return;

        final Position positionInFront = this.item.getPosition().positionInFront(this.item.getData().getRotation());

        if(!positionInFront.equals(entity.getPosition())) {
            habbo.getWalkComponent().walkToPosition(positionInFront.getX(), positionInFront.getY());
            return;
        }

        final int rotation = Position.calculateRotation(entity.getPosition(), this.item.getPosition(), false);

        if(!entity.hasStatus(RoomEntityStatus.SIT) && !entity.hasStatus(RoomEntityStatus.LAY)) {
            entity.setHeadRotation(rotation);
            entity.setBodyRotation(rotation);

            entity.setNeedsUpdate(true);
        }

        this.interactedEntity = entity;

        this.state = 0;
        this.setTickCounter(1);
    }

    @Override
    public void onTickCompleted() {
        switch (this.state) {
            case 0:
                this.item.getData().setExtraData("1");
                this.item.sendUpdate();

                this.state++;
                this.setTickCounter(0.5);

                break;
            case 1:
                this.interactedEntity.giveHandItem(this.item.getDefinition().getRandomVendingId());

                this.state++;
                this.setTickCounter(0.5);

                break;
            case 2:
                this.item.getData().setExtraData("0");
                this.item.sendUpdate();

                this.state = -1;
                this.interactedEntity = null;

                break;
        }
    }
}
