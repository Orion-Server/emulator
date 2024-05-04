package Orion.Api.Networking.Message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;

public interface IMessageComposer extends ByteBufHolder {
    int getId();

    void clear();

    boolean isFinished();

    void appendString(Object object);

    void appendDouble(double doubleValue);

    void appendInt(int integerValue);

    void appendLong(long longValue);

    void appendBoolean(Boolean booleanValue);

    void appendByte(int byteValue);

    void appendShort(int shortValue);

    ByteBuf getBuffer();
}
