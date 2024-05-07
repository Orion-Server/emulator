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

    // Messenger
    public static final int InitializeMessengerEvent = 2781;
    public static final int RequestMessengerFriendRequestsEvent = 2448;

    // Achievement
    public static final int RequestAchievementsEvent = 219;

    // Camera
    public static final int RequestCameraConfigurationEvent = 796;

    // HotelView
    public static final int RequestPromoArticlesEvent = 1827;
}
