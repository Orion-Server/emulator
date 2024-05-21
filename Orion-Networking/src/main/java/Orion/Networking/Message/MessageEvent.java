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

        if(buffer == null || buffer.readableBytes() <= 0) {
            this.buffer = Unpooled.EMPTY_BUFFER;

            if(buffer != null) buffer.release();
        } else {
            this.buffer = buffer;
        }

        this.id = this.buffer.readableBytes() >= 2 ? this.readShort() : 0;
    }

    @Override
    public short getId() {
        return this.id;
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public ByteBuf getBuffer() {
        return this.buffer;
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
        final ByteBuf buffer = this.buffer.readBytes(length);

        try {
            return buffer.toString(StandardCharsets.UTF_8);
        } finally {
            buffer.release();
        }
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
