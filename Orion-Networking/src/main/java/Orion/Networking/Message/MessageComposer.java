package Orion.Networking.Message;

import Orion.Api.Networking.Message.IMessageComposer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;

public class MessageComposer implements IMessageComposer {
    protected final int id;
    protected final ByteBuf buffer;

    public MessageComposer(short id, ByteBuf buffer) {
        this.id = id;
        this.buffer = buffer;

        try {
            this.buffer.writeInt(0);
            this.buffer.writeShort(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MessageComposer(int id, ByteBuf buffer) {
        this((short) id, buffer);
    }

    public MessageComposer(int id) {
        this(id, Unpooled.buffer());
    }

    @Override
    public ByteBuf content() {
        return this.buffer;
    }

    @Override
    public ByteBufHolder copy() {
        return new MessageComposer(this.id, this.buffer.copy());
    }

    @Override
    public ByteBufHolder duplicate() {
        return new MessageComposer(this.id, this.buffer.duplicate());
    }

    @Override
    public ByteBufHolder retainedDuplicate() {
        return new MessageComposer(this.id, this.buffer.retainedDuplicate());
    }

    @Override
    public ByteBufHolder replace(ByteBuf byteBuf) {
        return new MessageComposer(this.id, byteBuf);
    }

    @Override
    public int refCnt() {
        return this.buffer.refCnt();
    }

    @Override
    public ByteBufHolder retain() {
        return new MessageComposer(this.id, this.buffer.retain());
    }

    @Override
    public ByteBufHolder retain(int i) {
        return new MessageComposer(this.id, this.buffer.retain(i));
    }

    @Override
    public MessageComposer touch() {
        this.buffer.touch();

        return this;
    }

    @Override
    public MessageComposer touch(Object o) {
        this.buffer.touch(o);

        return this;
    }

    @Override
    public boolean release() {
        return this.buffer.release();
    }

    @Override
    public boolean release(int i) {
        return this.buffer.release(i);
    }

    public int getId() {
        return this.id;
    }

    public void clear() {
        this.buffer.clear();
    }

    public boolean hasLength() {
        return this.buffer.getInt(0) > -1;
    }

    public boolean isFinished() {
        return !this.hasLength();
    }

    public void appendString(Object object) {
        try {
            String string = "";

            if (object != null) {
                string = String.valueOf(object);
            }

            byte[] bytes = string.getBytes(StandardCharsets.UTF_8);

            this.buffer.writeShort(bytes.length);
            this.buffer.writeBytes(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appendDouble(double doubleValue) {
        try {
            this.appendString(Double.toString(doubleValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appendInt(int integer) {
        try {
            this.buffer.writeInt(integer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appendLong(long longValue) {
        try {
            this.buffer.writeLong(longValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void appendBoolean(Boolean booleanValue) {
        try {
            this.buffer.writeByte(booleanValue ? 1 : 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void appendByte(int byteValue) {
        try {
            this.buffer.writeByte(byteValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appendShort(int shortValue) {
        try {
            this.buffer.writeShort((short) shortValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ByteBuf getBuffer() {
        this.buffer.setInt(0, this.buffer.writerIndex() - 4);

        return this.buffer;
    }
}
