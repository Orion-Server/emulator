package Orion.Networking.Message;

import Orion.Api.Networking.Message.IMessageEvent;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;

public class MessageEvent implements IMessageEvent {
    private final short id;
    private final int length;
    private final ByteBuf buffer;

    public MessageEvent(int length, ByteBuf buffer) {
        this.length = length;
        this.buffer = buffer == null || buffer.readableBytes() <= 0 ? Unpooled.EMPTY_BUFFER : buffer;

        this.id = this.buffer.readableBytes() >= 2 ? this.readShort() : 0;
    }

    @Override
    public short getId() {
        return id;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public ByteBuf getBuffer() {
        return buffer;
    }

    @Override
    public short readShort() {
        return this.buffer.readShort();
    }

    @Override
    public int readInt() {
        return this.buffer.readInt();
    }

    @Override
    public String readString() {
        final int length = this.readShort();

        return new String(this.readBytes(length), StandardCharsets.UTF_8);
    }

    @Override
    public boolean readBoolean() {
        return this.buffer.readByte() == 1;
    }

    @Override
    public byte[] readBytes(int length) {
        final byte[] bytes = new byte[length];

        for(int i = 0; i < length; i++) {
            bytes[i] = this.buffer.readByte();
        }

        return bytes;
    }
}
