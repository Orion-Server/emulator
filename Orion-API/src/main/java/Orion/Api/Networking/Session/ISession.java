package Orion.Api.Networking.Session;

import Orion.Api.Networking.Message.IComposer;
import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.IHabbo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.List;

public interface ISession {
    ChannelHandlerContext getContext();

    void disconnect();

    int getId();

    void setClientVersion(String clientVersion);

    String getClientVersion();

    void handleIdleStateEvent(IdleStateEvent event);

    ISession send(IComposer composer);

    ISession send(IComposer... composers);

    ISession send(List<IComposer> composers);

    void setMachineId(String machineId);

    String getMachineId();

    String getIpAddress();

    void setHabbo(IHabbo habbo);

    IHabbo getHabbo();

    boolean isAuthenticated();

    boolean isDisposed();
}
