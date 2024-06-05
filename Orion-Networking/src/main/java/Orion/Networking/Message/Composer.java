package Orion.Networking.Message;

import Orion.Api.Networking.Message.IComposer;
import Orion.Api.Networking.Message.IMessageComposer;
import io.netty.buffer.ByteBuf;

public abstract class Composer implements IComposer {
    public Composer() {}

    public final IMessageComposer write(ByteBuf buffer) {
        final IMessageComposer composer = new MessageComposer(this.getId(), buffer);

        try {
            this.compose(composer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.dispose();
        }

        return composer;
    }

    public abstract short getId();

    public abstract void compose(final IMessageComposer msg);

    public void dispose() {}
}
