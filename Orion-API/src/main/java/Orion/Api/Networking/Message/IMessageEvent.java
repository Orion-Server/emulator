package Orion.Api.Networking.Message;

import io.netty.buffer.ByteBuf;

public interface IMessageEvent {
    short getId();

    int getLength();

    ByteBuf getBuffer();

    short readShort();

    int readInt();

    String readString();

    boolean readBoolean();

    byte[] readBytes(int length);
}
