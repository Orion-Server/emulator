package Orion.StressTest.Connection;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Codec.Message.Flash.FlashMessageDecoder;
import Orion.Networking.Codec.Message.Flash.FlashMessageEncoder;
import Orion.StressTest.Composer.ClientHelloComposer;
import Orion.StressTest.Composer.SSOTicketComposer;
import Orion.StressTest.Connection.Config.OrionClientConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class OrionClientConnection {
    private boolean isConnected = false;

    private boolean isOnline = false;
    private boolean isInRoom = false;
    private boolean isWalk = true;

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
            }
        });

        bootstrap.remoteAddress(config.getHostName(), config.getPort());

        ChannelFuture connectFuture = bootstrap.connect();

        connectFuture.addListener((future) -> {
            if (!future.isSuccess()) {
                System.out.println(STR."[\{config.getSsoTicket()}] Failed to connect to server!");
            } else {
                System.out.println(STR."[\{config.getSsoTicket()}] Connected to the server.");

                // we can do shit! :D
                this.isConnected = true;
                this.channel = connectFuture.channel();

                this.channel.writeAndFlush(new ClientHelloComposer());
                this.channel.writeAndFlush(new SSOTicketComposer(config.getSsoTicket()));
                this.isOnline = true;
            }
        });
    }

    public void disconnect() {
        if(!this.isConnected) {
            channel.disconnect();
        }
    }

    public void tick() {

    }

    public void send(IMessageComposer msg) {
        this.channel.writeAndFlush(msg);
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
