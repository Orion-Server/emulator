package Orion.Game.Habbo.Data.Inventory;

import Orion.Api.Server.Game.Habbo.Data.Inventory.IHabboInventoryBot;
import Orion.Api.Server.Game.Habbo.Enums.HabboGender;
import Orion.Api.Server.Game.Util.TimeUtil;
import Orion.Api.Storage.Result.IConnectionResult;

import java.util.ArrayList;
import java.util.Arrays;

public class HabboInventoryBot implements IHabboInventoryBot {
    private int id;
    private ArrayList<String> chatLines;
    private String name;
    private String motto;
    private String figure;
    private HabboGender gender;
    private int ownerId;
    private String ownerName;
    private boolean chatAuto;
    private boolean chatRandom;
    private double chatDelay;
    private int chatTimeOut;
    private int chatTimestamp;
    private short lastChatIndex;
    private int bubble;
    private String type;
    private int effect;
    private boolean canWalk = true;

    public HabboInventoryBot(final IConnectionResult data) {
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
    public ArrayList<String> getChatLines() {
        return this.chatLines;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getMotto() {
        return this.motto;
    }

    @Override
    public String getFigure() {
        return this.figure;
    }

    @Override
    public HabboGender getGender() {
        return this.gender;
    }

    @Override
    public int getOwnerId() {
        return this.ownerId;
    }

    @Override
    public String getOwnerName() {
        return this.ownerName;
    }

    @Override
    public boolean isChatAuto() {
        return this.chatAuto;
    }

    @Override
    public boolean isChatRandom() {
        return this.chatRandom;
    }

    @Override
    public double getChatDelay() {
        return this.chatDelay;
    }

    @Override
    public int getChatTimeOut() {
        return this.chatTimeOut;
    }

    @Override
    public int getChatTimestamp() {
        return this.chatTimestamp;
    }

    @Override
    public short getLastChatIndex() {
        return this.lastChatIndex;
    }

    @Override
    public int getBubble() {
        return this.bubble;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public int getEffect() {
        return this.effect;
    }

    @Override
    public boolean isCanWalk() {
        return this.canWalk;
    }

    @Override
    public void fill(final IConnectionResult data) throws Exception {
        this.id = data.getInt("id");
        this.name = data.getString("name");
        this.motto = data.getString("motto");
        this.figure = data.getString("figure");
        this.gender = HabboGender.valueOf(data.getString("gender"));
        this.ownerId = data.getInt("user_id");
        this.ownerName = data.getString("owner_name");
        this.chatAuto = data.getString("chat_auto").equals("1");
        this.chatRandom = data.getString("chat_random").equals("1");
        this.chatDelay = data.getDouble("chat_delay");
        this.chatLines = new ArrayList<>(Arrays.asList(data.getString("chat_lines").split("\r")));
        this.type = data.getString("type");
        this.effect = data.getInt("effect");
        this.canWalk = data.getString("freeroam").equals("1");
        this.chatTimeOut = (int) (TimeUtil.now() + this.chatDelay);
        this.bubble = data.getInt("bubble_id");
    }
}
