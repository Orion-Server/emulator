package Orion.Networking.Message;

import Orion.Api.Networking.Message.IMessageComposer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;

public class MessageComposer implements IMessageComposer {
    protected final int id;
    protected final ByteBuf buffer;

    public MessageComposer(short id, ByteBuf buffer) {
        this.id = id;
        this.buffer = buffer;

        try {
            this.buffer.writeInt(-1);
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
    public IMessageComposer copy() {
        return new MessageComposer(this.id, this.buffer.copy());
    }

    @Override
    public IMessageComposer duplicate() {
        return new MessageComposer(this.id, this.buffer.duplicate());
    }

    @Override
    public IMessageComposer retainedDuplicate() {
        return new MessageComposer(this.id, this.buffer.retainedDuplicate());
    }

    @Override
    public IMessageComposer replace(ByteBuf byteBuf) {
        return new MessageComposer(this.id, byteBuf);
    }

    @Override
    public int refCnt() {
        return this.buffer.refCnt();
    }

    @Override
    public IMessageComposer retain() {
        return new MessageComposer(this.id, this.buffer.retain());
    }

    @Override
    public IMessageComposer retain(int i) {
        return new MessageComposer(this.id, this.buffer.retain(i));
    }

    @Override
    public IMessageComposer touch() {
        this.buffer.touch();

        return this;
    }

    @Override
    public IMessageComposer touch(Object o) {
        this.buffer.touch(o);

        return this;
    }

    @Override
    public boolean release() {
        if(this.buffer.refCnt() <= 1) return false;

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
