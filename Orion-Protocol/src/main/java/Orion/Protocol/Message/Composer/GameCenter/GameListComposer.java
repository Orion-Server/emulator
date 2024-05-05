package Orion.Protocol.Message.Composer.GameCenter;

import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class GameListComposer extends MessageComposer {
    public GameListComposer() {
        super(ComposerHeaders.GameListComposer);

        appendInt(2);//Count

        appendInt(0);
        appendString("snowwar");
        appendString("93d4f3");
        appendString("");
        appendString("http://habboo-a.akamaihd.net/gamecenter/Sulake/snowwar/20130214010101/");
        appendString("");

        appendInt(3);
        appendString("basejump");
        appendString("68bbd2"); //Background Color
        appendString(""); //Text color
        appendString("http://habboo-a.akamaihd.net/gamecenter/Sulake/basejump/20130214010101/");
        appendString("");

        appendInt(4);
        appendString("slotcar");
        appendString("4a95df");
        appendString("");
        appendString("http://habboo-a.akamaihd.net/gamecenter/Sulake/slotcar/20130214010101/");
        appendString("");
    }
}
