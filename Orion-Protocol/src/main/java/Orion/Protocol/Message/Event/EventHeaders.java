package Orion.Protocol.Message.Event;

public abstract class EventHeaders {
    // Handshake
    public static final int ClientHelloEvent = 4000;
    public static final int MachineIDEvent = 2490;
    public static final int SSOTicketEvent = 2419;

    // Lifecycle
    public static final int PingEvent = 295;
    public static final int PongEvent = 2596;
    public static final int MemoryPerformanceEvent = 3230;

    // Habbo
    public static final int RequestHabboDataEvent = 357;
    public static final int RequestHabboProfileEvent = 3265;
    public static final int RequestHabboCurrenciesEvent = 273;
    public static final int RequestNavigatorSettingsEvent = 1782;
    public static final int RequestEntityWalkEvent = 3320;
    public static final int LookAtEntityEvent = 3301;
    public static final int HabboStartsTypingEvent = 1597;
    public static final int HabboStopTypingEvent = 1474;
    public static final int HabboChatMessageEvent = 1314;
    public static final int RequestEntityDanceEvent = 2080;
    public static final int RequestEntitySitEvent = 2235;
    public static final int RequestEntityActionEvent = 2456;
    public static final int ApplyEntitySignEvent = 1975;
    public static final int RequestInventoryDataEvent = 3150;
    public static final int RequestInventoryBotsEvent = 3848;

    // Messenger
    public static final int InitializeMessengerEvent = 2781;
    public static final int RequestMessengerFriendRequestsEvent = 2448;

    // Achievement
    public static final int RequestAchievementsEvent = 219;

    // Camera
    public static final int RequestCameraConfigurationEvent = 796;

    // HotelView
    public static final int RequestPromoArticlesEvent = 1827;

    // Navigator
    public static final int RequestNavigatorDataEvent = 2110;
    public static final int RequestNavigatorRoomsEvent = 249;
    public static final int RequestCanCreateRoomEvent = 2128;

    // Room
    public static final int JoinRoomEvent = 685;
    public static final int RequestRoomLoadEvent = 2312;
    public static final int RequestRoomCategoriesEvent = 3027;
    public static final int RequestRoomDataEvent = 2230;
    public static final int RequestHeightmapEvent = 3898;
    public static final int RequestRoomHeightmapEvent = 2300;
    public static final int RequestItemMovementEvent = 248;
    public static final int RequestCreateRoomEvent = 2752;
    public static final int ToggleFloorItemStateEvent = 99;
}
