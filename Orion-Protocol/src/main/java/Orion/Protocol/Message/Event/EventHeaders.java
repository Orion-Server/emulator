package Orion.Protocol.Message.Event;

public abstract class EventHeaders {
    // Handshake
    public static final int ClientHelloEvent = 4000;
    public static final int SSOTicketEvent = 2419;

    // Lifecycle
    public static final int PingEvent = 2596;
}
