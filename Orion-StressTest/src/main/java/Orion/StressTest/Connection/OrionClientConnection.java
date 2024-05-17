package Orion.StressTest.Connection;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Codec.Message.Flash.FlashMessageDecoder;
import Orion.Networking.Codec.Message.Flash.FlashMessageEncoder;
import Orion.StressTest.Composer.RequestEntityWalkComposer;
import Orion.StressTest.Composer.SSOTicketComposer;
import Orion.StressTest.Connection.Config.OrionClientConfig;
import Orion.StressTest.OrionStressTest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class OrionClientConnection {
    private boolean isConnected = false;

    private boolean isOnline = false;
    private boolean isInRoom = false;
    private boolean isWalk = false;

    private Channel channel;

    public OrionClientConnection(OrionClientConfig config, EventLoopGroup loopGroup) {
        final Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(loopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true);

        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) {
                socketChannel.pipeline().addLast("encoder", new FlashMessageEncoder());
                socketChannel.pipeline().addLast("decoder", new FlashMessageDecoder());
                socketChannel.pipeline().addLast("idleHandler", new IdleStateHandler(90, 60, 30));
            }
        });

        bootstrap.remoteAddress(config.getHostName(), config.getPort());

        ChannelFuture connectFuture = bootstrap.connect();

        connectFuture.addListener((future) -> {
            if (!future.isSuccess()) {
                System.out.println(STR."[\{config.getSsoTicket()}] Failed to connect to server!");
                return;
            }

            System.out.println(STR."[\{config.getSsoTicket()}] Connected to the server.");

            this.channel = connectFuture.channel();
            this.isConnected = this.channel.isActive() && this.channel.isOpen();

            this.channel.writeAndFlush(new SSOTicketComposer(config.getSsoTicket()));
            this.isOnline = true;
        });
    }

    public void disconnect() {
        if(!this.isConnected) return;

        channel.disconnect();
    }

    public void tick() {
        if(!this.isConnected() || !this.isOnline()) {
            this.disconnect();
            return;
        }

        if(this.isInRoom() && this.isWalk()) {
            int x = OrionStressTest.getRandom(1, 32);
            int y = OrionStressTest.getRandom(0, 32);

            this.send(new RequestEntityWalkComposer(x, y));
        }
    }

    public void send(IMessageComposer msg) {
        this.channel.writeAndFlush(msg, this.channel.voidPromise());
    }

    public boolean isOnline() {
        return this.isOnline;
    }

    public boolean isConnected() {
        return this.isConnected;
    }

    public boolean isInRoom() {
        return this.isInRoom;
    }

    public void setIsInRoom(boolean isInRoom) {
        this.isInRoom = isInRoom;
    }

    public boolean isWalk() {
        return this.isWalk;
    }

    public void setIsWalk(boolean isWalk) {
        this.isWalk = isWalk;
    }
}
