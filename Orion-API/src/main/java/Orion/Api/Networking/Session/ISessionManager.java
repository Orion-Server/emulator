package Orion.Api.Networking.Session;

import Orion.Api.Networking.Session.Throttle.IAddressAttempt;
import io.netty.channel.ChannelHandlerContext;

public interface ISessionManager {
    boolean addChannel(ChannelHandlerContext channel, String ipAddress);

    void createOrUpdateConnectionAttempt(String address);

    IAddressAttempt getConnectionAttempt(String address);

    void removeSession(ISession session);
}
