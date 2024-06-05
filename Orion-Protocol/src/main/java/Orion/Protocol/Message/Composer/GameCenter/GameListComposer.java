package Orion.Protocol.Message.Composer.GameCenter;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class GameListComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.GameListComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(2);//Count

        msg.appendInt(0);
        msg.appendString("snowwar");
        msg.appendString("93d4f3");
        msg.appendString("");
        msg.appendString("http://habboo-a.akamaihd.net/gamecenter/Sulake/snowwar/20130214010101/");
        msg.appendString("");

        msg.appendInt(3);
        msg.appendString("basejump");
        msg.appendString("68bbd2"); //Background Color
        msg.appendString(""); //Text color
        msg.appendString("http://habboo-a.akamaihd.net/gamecenter/Sulake/basejump/20130214010101/");
        msg.appendString("");

        msg.appendInt(4);
        msg.appendString("slotcar");
        msg.appendString("4a95df");
        msg.appendString("");
        msg.appendString("http://habboo-a.akamaihd.net/gamecenter/Sulake/slotcar/20130214010101/");
        msg.appendString("");
    }
}
