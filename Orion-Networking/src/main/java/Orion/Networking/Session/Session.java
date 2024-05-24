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

    private final ChannelHandlerContext context;

    private String machineId = null;

    private final String ipAddress;

    private IHabbo habbo;

    private boolean isDisposed = false;

    public Session(int id, ChannelHandlerContext context, String ipAddress) {
        this.id = id;
        this.context = context;
        this.ipAddress = ipAddress;
    }

    @Override
    public ChannelHandlerContext getContext() {
        return this.context;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void disconnect() {
        if(this.isDisposed) return;

        this.isDisposed = true;

        this.setHabbo(null);
        this.getContext().disconnect();
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
    public boolean isAuthenticated() {
        return this.habbo != null && this.context.channel().isOpen();
    }

    @Override
    public boolean isDisposed() {
        return this.isDisposed || !this.context.channel().isOpen();
    }

    @Override
    public void handleIdleStateEvent(IdleStateEvent event) {
        switch (event.state()) {
            case READER_IDLE:
                this.getContext().close();
                break;
            case WRITER_IDLE:
                this.getContext().writeAndFlush(new MessageComposer(3928)); // TODO: Maybe have a way to solve the module cycles issue?
                break;
        }
    }

    @Override
    public ISession send(IMessageComposer composer) {
        if(!this.context.channel().isOpen()) return this;

        //this.logger.debug(STR."<< Composing [\{composer.getId()}] \{composer.getClass().getName()}");

        this.context.writeAndFlush(composer, this.context.voidPromise());

        return this;
    }

    @Override
    public ISession send(IMessageComposer... composers) {
        if(!this.context.channel().isOpen()) return this;

        for (final IMessageComposer composer : composers) {
            //this.logger.debug(STR."<< Composing [\{composer.getId()}] \{composer.getClass().getSimpleName()}");

            this.context.write(composer);
        }

        this.context.flush();

        return this;
    }

    @Override
    public ISession send(List<IMessageComposer> composers) {
        if(!this.context.channel().isOpen()) return this;

        for (final IMessageComposer composer : composers) {
            //this.logger.debug(STR."<< Composing [\{composer.getId()}] \{composer.getClass().getSimpleName()}");

            this.context.write(composer);
        }

        this.context.flush();

        return this;
    }
}
