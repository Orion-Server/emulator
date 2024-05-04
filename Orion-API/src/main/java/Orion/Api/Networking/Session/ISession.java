package Orion.Api.Networking.Session;

import Orion.Api.Networking.Message.IMessageComposer;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public interface ISession {
    ChannelHandlerContext getChannel();

    void disconnect();

    int getId();

    void setClientVersion(String clientVersion);

    String getClientVersion();

    ISession send(IMessageComposer composer);

    ISession send(IMessageComposer... composers);

    ISession send(List<IMessageComposer> composers);
}
