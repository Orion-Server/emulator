package Orion.Api.Networking.Session;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.IHabbo;
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

    void setMachineId(String machineId);

    String getMachineId();

    String getIpAddress();

    void setHabbo(IHabbo habbo);

    IHabbo getHabbo();
}
