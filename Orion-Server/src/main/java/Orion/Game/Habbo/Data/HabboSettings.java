package Orion.Game.Habbo.Data;

import Orion.Api.Server.Game.Habbo.Data.IHabboSettings;
import Orion.Api.Storage.Result.IConnectionResult;

public class HabboSettings implements IHabboSettings {
    private int respectPointsReceived;
    private int achievementScore;
    private int respectPointsGiven;
    private int petRespectPointsToGive;
    private int respectPointsToGive;
    private boolean blockFollowing;
    private boolean blockFriendRequests;
    private boolean blockRoomInvites;
    private boolean preferOldChat;
    private boolean blockCameraFollow;
    private int guild;
    private String[] tags;
    private boolean allowTrade;
    private int clubExpireTimestamp;
    private int loginStreak;
    private int rentedItemId;
    private int rentedTimeEnd;
    private int systemVolume;
    private int furnitureVolume;
    private int traxVolume;
    private int chatColor;
    private int hofPoints;
    private boolean blockStaffAlerts;
    private int citizenshipLevel;
    private int helpersLevel;
    private boolean ignoreBots;
    private boolean ignorePets;
    private boolean nux;
    private int muteEndTime;
    private boolean allowNameChange;
    private boolean perkTrade;
    private int forumPostsCount;
    private int uiFlags;
    private boolean hasGottenDefaultSavedSearches;
    private int maxFriends;
    private int maxRooms;
    private int lastHCPayday;
    private int hcGiftsClaimed;

    public HabboSettings(IConnectionResult data) {
        try {
            this.fill(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRespectPointsReceived() {
        return this.respectPointsReceived;
    }

    @Override
    public int getAchievementScore() {
        return this.achievementScore;
    }

    @Override
    public int getRespectPointsGiven() {
        return this.respectPointsGiven;
    }

    @Override
    public int getPetRespectPointsToGive() {
        return this.petRespectPointsToGive;
    }

    @Override
    public int getRespectPointsToGive() {
        return this.respectPointsToGive;
    }

    @Override
    public boolean isBlockFollowing() {
        return this.blockFollowing;
    }

    @Override
    public boolean isBlockFriendRequests() {
        return this.blockFriendRequests;
    }

    @Override
    public boolean isBlockRoomInvites() {
        return this.blockRoomInvites;
    }

    @Override
    public boolean isPreferOldChat() {
        return this.preferOldChat;
    }

    @Override
    public boolean isBlockCameraFollow() {
        return this.blockCameraFollow;
    }

    @Override
    public int getGuild() {
        return this.guild;
    }

    @Override
    public String[] getTags() {
        return this.tags;
    }

    @Override
    public boolean allowTrade() {
        return this.allowTrade;
    }

    @Override
    public int getClubExpireTimestamp() {
        return this.clubExpireTimestamp;
    }

    @Override
    public int getLoginStreak() {
        return this.loginStreak;
    }

    @Override
    public int getRentedItemId() {
        return this.rentedItemId;
    }

    @Override
    public int getRentedTimeEnd() {
        return this.rentedTimeEnd;
    }

    @Override
    public int getSystemVolume() {
        return this.systemVolume;
    }

    @Override
    public int getFurnitureVolume() {
        return this.furnitureVolume;
    }

    @Override
    public int getTraxVolume() {
        return this.traxVolume;
    }

    @Override
    public int getChatColor() {
        return this.chatColor;
    }

    @Override
    public int getHofPoints() {
        return this.hofPoints;
    }

    @Override
    public boolean isBlockStaffAlerts() {
        return this.blockStaffAlerts;
    }

    @Override
    public int getCitizenshipLevel() {
        return this.citizenshipLevel;
    }

    @Override
    public int getHelpersLevel() {
        return this.helpersLevel;
    }

    @Override
    public boolean isIgnoreBots() {
        return this.ignoreBots;
    }

    @Override
    public boolean isIgnorePets() {
        return this.ignorePets;
    }

    @Override
    public boolean isNux() {
        return this.nux;
    }

    @Override
    public int getMuteEndTime() {
        return this.muteEndTime;
    }

    @Override
    public boolean allowNameChange() {
        return this.allowNameChange;
    }

    @Override
    public boolean isPerkTrade() {
        return this.perkTrade;
    }

    @Override
    public int getForumPostsCount() {
        return this.forumPostsCount;
    }

    @Override
    public int getUiFlags() {
        return this.uiFlags;
    }

    @Override
    public boolean isHasGottenDefaultSavedSearches() {
        return this.hasGottenDefaultSavedSearches;
    }

    @Override
    public int getMaxFriends() {
        return this.maxFriends;
    }

    @Override
    public int getMaxRooms() {
        return this.maxRooms;
    }

    @Override
    public int getLastHCPayday() {
        return this.lastHCPayday;
    }

    @Override
    public int getHcGiftsClaimed() {
        return this.hcGiftsClaimed;
    }

    @Override
    public void fill(IConnectionResult result) throws Exception {
        this.achievementScore = result.getInt("achievement_score");
        this.respectPointsReceived = result.getInt("respects_received");
        this.respectPointsGiven = result.getInt("respects_given");
        this.petRespectPointsToGive = result.getInt("daily_pet_respect_points");
        this.respectPointsToGive = result.getInt("daily_respect_points");
        this.blockFollowing = result.getBoolean("block_following");
        this.blockFriendRequests = result.getBoolean("block_friendrequests");
        this.blockRoomInvites = result.getBoolean("block_roominvites");
        this.preferOldChat = result.getBoolean("old_chat");
        this.blockCameraFollow = result.getBoolean("block_camera_follow");
        this.guild = result.getInt("guild_id");
        this.tags = result.getString("tags").split(";");
        this.allowTrade = result.getBoolean("can_trade");
        this.clubExpireTimestamp = result.getInt("club_expire_timestamp");
        this.loginStreak = result.getInt("login_streak");
        this.rentedItemId = result.getInt("rent_space_id");
        this.rentedTimeEnd = result.getInt("rent_space_endtime");
        this.systemVolume = result.getInt("volume_system");
        this.furnitureVolume = result.getInt("volume_furni");
        this.traxVolume = result.getInt("volume_trax");
        this.chatColor = result.getInt("chat_color"); // TODO: Implement chat color
        this.hofPoints = result.getInt("hof_points");
        this.blockStaffAlerts = result.getBoolean("block_alerts");
        this.citizenshipLevel = result.getInt("talent_track_citizenship_level");
        this.helpersLevel = result.getInt("talent_track_helpers_level");
        this.ignoreBots = result.getBoolean("ignore_bots");
        this.ignorePets = result.getBoolean("ignore_pets");
        this.nux = result.getBoolean("nux");
        this.muteEndTime = result.getInt("mute_end_timestamp");
        this.allowNameChange = result.getBoolean("allow_name_change");
        this.perkTrade = result.getBoolean("perk_trade");
        this.forumPostsCount = result.getInt("forums_post_count");
        this.uiFlags = result.getInt("ui_flags");
        this.hasGottenDefaultSavedSearches = result.getBoolean("has_gotten_default_saved_searches");
        this.maxFriends = result.getInt("max_friends");
        this.maxRooms = result.getInt("max_rooms");
        this.lastHCPayday = result.getInt("last_hc_payday");
        this.hcGiftsClaimed = result.getInt("hc_gifts_claimed");
    }

    @Override
    public void dispose() {
        this.tags = null;
    }
}
