package Orion.Protocol.Message.Composer.Habbo;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboPerksComposer extends Composer {
    private final IHabbo habbo;

    public HabboPerksComposer(final IHabbo habbo) {
        this.habbo = habbo;
    }

    @Override
    public short getId() {
        return ComposerHeaders.HabboPerksComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(15);

        msg.appendString("USE_GUIDE_TOOL");
        msg.appendString("requirement.unfulfilled.helper_level_4");
        msg.appendBoolean(this.habbo.getPermission().hasAccountPermission("helper_use_guide_tool"));

        msg.appendString("GIVE_GUIDE_TOURS");
        msg.appendString("");
        msg.appendBoolean(this.habbo.getPermission().hasAccountPermission("helper_give_guide_tours"));

        msg.appendString("JUDGE_CHAT_REVIEWS");
        msg.appendString("requirement.unfulfilled.helper_level_6");
        msg.appendBoolean(this.habbo.getPermission().hasAccountPermission("helper_judge_chat_reviews"));

        msg.appendString("VOTE_IN_COMPETITIONS");
        msg.appendString("requirement.unfulfilled.helper_level_2");
        msg.appendBoolean(true);

        msg.appendString("CALL_ON_HELPERS");
        msg.appendString("");
        msg.appendBoolean(true);

        msg.appendString("CITIZEN");
        msg.appendString("");
        msg.appendBoolean(true);

        msg.appendString("TRADE");
        msg.appendString("requirement.unfulfilled.no_trade_lock");
        msg.appendBoolean(this.habbo.getSettings().allowTrade());

        msg.appendString("HEIGHTMAP_EDITOR_BETA");
        msg.appendString("requirement.unfulfilled.feature_disabled");
        msg.appendBoolean(this.habbo.getPermission().hasAccountPermission("floorplan_editor"));

        msg.appendString("BUILDER_AT_WORK");
        msg.appendString("");
        msg.appendBoolean(true);

        msg.appendString("CALL_ON_HELPERS");
        msg.appendString("");
        msg.appendBoolean(true);

        msg.appendString("CAMERA");
        msg.appendString("");
        msg.appendBoolean(this.habbo.getPermission().hasAccountPermission("camera"));

        msg.appendString("NAVIGATOR_PHASE_TWO_2014");
        msg.appendString("");
        msg.appendBoolean(true);

        msg.appendString("MOUSE_ZOOM");
        msg.appendString("");
        msg.appendBoolean(true);

        msg.appendString("NAVIGATOR_ROOM_THUMBNAIL_CAMERA");
        msg.appendString("");
        msg.appendBoolean(true);

        msg.appendString("HABBO_CLUB_OFFER_BETA");
        msg.appendString("");
        msg.appendBoolean(true);
    }
}
