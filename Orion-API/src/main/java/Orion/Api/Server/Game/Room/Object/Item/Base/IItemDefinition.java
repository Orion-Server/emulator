package Orion.Api.Server.Game.Room.Object.Item.Base;

import Orion.Api.Server.Game.Room.Object.Item.ItemDefinitionType;
import Orion.Api.Util.IFillable;

public interface IItemDefinition extends IFillable {
    int getId();

    int getSpriteId();

    String getPublicName();

    String getItemName();

    ItemDefinitionType getType();

    int getWidth();

    int getLength();

    double getStackHeight();

    boolean isAllowStack();

    boolean isAllowSit();

    boolean isAllowLay();

    boolean isAllowWalk();

    boolean isAllowTrade();

    boolean isAllowRecycle();

    boolean isAllowMarketplaceSell();

    boolean isAllowInventoryStack();

    String getInteractionType();

    int getInteractionModesCount();

    String getCustomParams();

    int getMaleEffect();

    int getFemaleEffect();

    String getClothingOnWalk();

    Integer[] getVendingIds();

    Integer getRandomVendingId();

    Double[] getMultiHeights();
}
