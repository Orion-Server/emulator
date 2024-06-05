package Orion.Api.Networking.Message;

import io.netty.buffer.ByteBuf;

public interface IComposer {
    IMessageComposer write(ByteBuf buffer);
}
