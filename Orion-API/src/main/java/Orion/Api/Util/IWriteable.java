package Orion.Api.Util;

import Orion.Api.Networking.Message.IMessageComposer;

public interface IWriteable {
    void write(final IMessageComposer composer);
}
