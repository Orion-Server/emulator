package Orion.Networking.Session;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.MessageComposer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Session implements ISession {
    private final Logger logger = LogManager.getLogger();

    private final int id;

    private String clientVersion;

    private final ChannelHandlerContext channel;

    private String machineId = null;

    private String ipAddress;

    private IHabbo habbo;

    public Session(int id, ChannelHandlerContext channel, String ipAddress) {
        this.id = id;
        this.channel = channel;
        this.ipAddress = ipAddress;
    }

    @Override
    public ChannelHandlerContext getChannel() {
        return this.channel;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void disconnect() {
        this.onDisconnect();
        this.getChannel().disconnect();
    }

    private void onDisconnect() {
        // TODO: implement onDisconnect
    }

    @Override
    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    @Override
    public String getClientVersion() {
        return this.clientVersion;
    }

    @Override
    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    @Override
    public String getMachineId() {
        return this.machineId;
    }

    @Override
    public String getIpAddress() {
        return this.ipAddress;
    }

    @Override
    public void setHabbo(IHabbo habbo) {
        this.habbo = habbo;
    }

    @Override
    public IHabbo getHabbo() {
        return this.habbo;
    }

    @Override
    public void handleIdleStateEvent(IdleStateEvent event) {
        switch (event.state()) {
            case READER_IDLE:
                this.getChannel().close();
                break;
            case WRITER_IDLE:
                this.getChannel().writeAndFlush(new MessageComposer(3928)); // TODO: Maybe have a way to solve the module cycles?
                break;
        }
    }

    @Override
    public ISession send(IMessageComposer composer) {
        if(!this.channel.channel().isOpen()) return this;

        this.logger.debug(STR."Composing [\{composer.getId()}] \{composer.getClass().getName()}");

        this.channel.writeAndFlush(composer);

        return this;
    }

    @Override
    public ISession send(IMessageComposer... composers) {
        if(!this.channel.channel().isOpen()) return this;

        for (final IMessageComposer composer : composers) {
            this.logger.debug(STR."Composing [\{composer.getId()}] \{composer.getClass().getSimpleName()}");

            this.channel.write(composer);
        }

        this.channel.flush();

        return this;
    }

    @Override
    public ISession send(List<IMessageComposer> composers) {
        if(!this.channel.channel().isOpen()) return this;

        for (final IMessageComposer composer : composers) {
            this.logger.debug(STR."Composing [\{composer.getId()}] \{composer.getClass().getSimpleName()}");

            this.channel.write(composer);
        }

        this.channel.flush();

        return this;
    }
}
