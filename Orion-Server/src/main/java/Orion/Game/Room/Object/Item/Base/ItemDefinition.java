package Orion.Game.Room.Object.Item.Base;

import Orion.Api.Server.Game.Room.Object.Item.Base.IItemDefinition;
import Orion.Api.Server.Game.Room.Object.Item.ItemDefinitionType;
import Orion.Api.Storage.Result.IConnectionResult;

public class ItemDefinition implements IItemDefinition {
    private int id;
    private int spriteId;
    private String publicName;
    private String itemName;
    private ItemDefinitionType type;
    private int width;
    private int length;
    private double stackHeight;
    private boolean allowStack;
    private boolean allowSit;
    private boolean allowLay;
    private boolean allowWalk;
    private boolean allowTrade;
    private boolean allowRecycle;
    private boolean allowMarketplaceSell;
    private boolean allowInventoryStack;
    private String interactionType;
    private int interactionModesCount;
    private String customParams;
    private int maleEffect;
    private int femaleEffect;
    private String clothingOnWalk;

    private Integer[] vendingIds;
    private Double[] multiHeights;

    public ItemDefinition(final IConnectionResult data) {
        try {
            this.fill(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getSpriteId() {
        return this.spriteId;
    }

    @Override
    public String getPublicName() {
        return this.publicName;
    }

    @Override
    public String getItemName() {
        return this.itemName;
    }

    @Override
    public ItemDefinitionType getType() {
        return this.type;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public double getStackHeight() {
        return this.stackHeight;
    }

    @Override
    public boolean isAllowStack() {
        return this.allowStack;
    }

    @Override
    public boolean isAllowSit() {
        return this.allowSit;
    }

    @Override
    public boolean isAllowLay() {
        return this.allowLay;
    }

    @Override
    public boolean isAllowWalk() {
        return this.allowWalk;
    }

    @Override
    public boolean isAllowTrade() {
        return this.allowTrade;
    }

    @Override
    public boolean isAllowRecycle() {
        return this.allowRecycle;
    }

    @Override
    public boolean isAllowMarketplaceSell() {
        return this.allowMarketplaceSell;
    }

    @Override
    public boolean isAllowInventoryStack() {
        return this.allowInventoryStack;
    }

    @Override
    public String getInteractionType() {
        return this.interactionType;
    }

    @Override
    public int getInteractionModesCount() {
        return this.interactionModesCount;
    }

    @Override
    public String getCustomParams() {
        return this.customParams;
    }

    @Override
    public int getMaleEffect() {
        return this.maleEffect;
    }

    @Override
    public int getFemaleEffect() {
        return this.femaleEffect;
    }

    @Override
    public String getClothingOnWalk() {
        return this.clothingOnWalk;
    }

    @Override
    public Integer[] getVendingIds() {
        return this.vendingIds;
    }

    @Override
    public Integer getRandomVendingId() {
        if (this.vendingIds == null || this.vendingIds.length == 0) {
            return null;
        }

        try {
            return this.vendingIds[(int) Math.floor(Math.random() * this.vendingIds.length)];
        } catch (Exception e) {
            return this.vendingIds[0];
        }
    }

    @Override
    public Double[] getMultiHeights() {
        return this.multiHeights;
    }

    @Override
    public boolean isDecoration() {
        return this.getItemName().equalsIgnoreCase("floor")
                || this.getItemName().equalsIgnoreCase("wallpaper")
                || this.getItemName().equalsIgnoreCase("landscape")
                || this.getItemName().equalsIgnoreCase("poster");
    }

    public void fill(IConnectionResult data) throws Exception {
        this.id = data.getInt("id");
        this.spriteId = data.getInt("sprite_id");
        this.publicName = data.getString("public_name");
        this.itemName = data.getString("item_name");
        this.width = data.getInt("width");
        this.length = data.getInt("length");
        this.stackHeight = data.getDouble("stack_height");
        this.allowStack = data.getBoolean("allow_stack");
        this.allowSit = data.getBoolean("allow_sit");
        this.allowLay = data.getBoolean("allow_lay");
        this.allowWalk = data.getBoolean("allow_walk");
        this.allowTrade = data.getBoolean("allow_trade");
        this.allowRecycle = data.getBoolean("allow_recycle");
        this.allowMarketplaceSell = data.getBoolean("allow_marketplace_sell");
        this.allowInventoryStack = data.getBoolean("allow_inventory_stack");
        this.interactionType = data.getString("interaction_type");
        this.interactionModesCount = data.getInt("interaction_modes_count");
        this.customParams = data.getString("customparams");
        this.maleEffect = data.getInt("effect_id_male");
        this.femaleEffect = data.getInt("effect_id_female");
        this.clothingOnWalk = data.getString("clothing_on_walk");

        try {
            this.type = ItemDefinitionType.fromResult(data.getString("type"));
        } catch (IllegalArgumentException e) {
            this.type = ItemDefinitionType.FLOOR;
        }

        if (data.getString("vending_ids") != null && !data.getString("vending_ids").isEmpty()) {
            final String[] vendingIds = data.getString("vending_ids").replace(";", ",").split(",");

            this.vendingIds = new Integer[vendingIds.length];

            for (int i = 0; i < vendingIds.length; i++) {
                this.vendingIds[i] = Integer.parseInt(vendingIds[i].replace(" ", ""));
            }
        }

        if (data.getString("multiheight") != null && data.getString("multiheight").contains(";")) {
            final String[] multiHeights = data.getString("multiheight").split(";");

            this.multiHeights = new Double[multiHeights.length];

            for (int i = 0; i < multiHeights.length; i++) {
                this.multiHeights[i] = Double.parseDouble(multiHeights[i]);
            }
        }
    }
}
