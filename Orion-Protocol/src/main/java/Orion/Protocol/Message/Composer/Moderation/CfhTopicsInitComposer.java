package Orion.Protocol.Message.Composer.Moderation;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class CfhTopicsInitComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.CfhTopicsInitComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(6);
        msg.appendString("sex_and_pii");
        msg.appendInt(8);
        msg.appendString("sexual_webcam_images");
        msg.appendInt(1);
        msg.appendString("mods");
        msg.appendString("sexual_webcam_images_auto");
        msg.appendInt(2);
        msg.appendString("mods");
        msg.appendString("explicit_sexual_talk");
        msg.appendInt(3);
        msg.appendString("mods");
        msg.appendString("cybersex");
        msg.appendInt(4);
        msg.appendString("mods");
        msg.appendString("cybersex_auto");
        msg.appendInt(5);
        msg.appendString("mods");
        msg.appendString("meet_some");
        msg.appendInt(6);
        msg.appendString("mods");
        msg.appendString("meet_irl");
        msg.appendInt(7);
        msg.appendString("mods");
        msg.appendString("email_or_phone");
        msg.appendInt(8);
        msg.appendString("mods");
        msg.appendString("scamming");
        msg.appendInt(3);
        msg.appendString("stealing");
        msg.appendInt(9);
        msg.appendString("mods");
        msg.appendString("scamsites");
        msg.appendInt(10);
        msg.appendString("mods");
        msg.appendString("selling_buying_accounts_or_furni");
        msg.appendInt(11);
        msg.appendString("mods");
        msg.appendString("trolling");
        msg.appendInt(11);
        msg.appendString("hate_speech");
        msg.appendInt(12);
        msg.appendString("mods");
        msg.appendString("violent_roleplay");
        msg.appendInt(13);
        msg.appendString("mods");
        msg.appendString("swearing");
        msg.appendInt(14);
        msg.appendString("auto_reply");
        msg.appendString("drugs");
        msg.appendInt(15);
        msg.appendString("mods");
        msg.appendString("gambling");
        msg.appendInt(16);
        msg.appendString("mods");
        msg.appendString("self_threatening");
        msg.appendInt(17);
        msg.appendString("mods");
        msg.appendString("mild_staff_impersonation");
        msg.appendInt(18);
        msg.appendString("mods");
        msg.appendString("severe_staff_impersonation");
        msg.appendInt(19);
        msg.appendString("mods");
        msg.appendString("habbo_name");
        msg.appendInt(20);
        msg.appendString("mods");
        msg.appendString("minors_access");
        msg.appendInt(21);
        msg.appendString("mods");
        msg.appendString("bullying");
        msg.appendInt(22);
        msg.appendString("guardians");
        msg.appendString("interruption");
        msg.appendInt(2);
        msg.appendString("flooding");
        msg.appendInt(23);
        msg.appendString("mods");
        msg.appendString("doors");
        msg.appendInt(24);
        msg.appendString("mods");
        msg.appendString("room");
        msg.appendInt(1);
        msg.appendString("room_report");
        msg.appendInt(25);
        msg.appendString("mods");
        msg.appendString("help");
        msg.appendInt(2);
        msg.appendString("help_habbo");
        msg.appendInt(26);
        msg.appendString("auto_reply");
        msg.appendString("help_payments");
        msg.appendInt(27);
        msg.appendString("auto_reply");
    }
}
