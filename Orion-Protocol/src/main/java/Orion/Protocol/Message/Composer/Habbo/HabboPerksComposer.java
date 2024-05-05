package Orion.Protocol.Message.Composer.Habbo;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Permission.IPermissionManager;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HabboPerksComposer extends MessageComposer {
    public HabboPerksComposer(
            final IHabbo habbo,
            final IPermissionManager permissionManager
    ) {
        super(ComposerHeaders.HabboPerksComposer);

        appendInt(15);

        appendString("USE_GUIDE_TOOL");
        appendString("requirement.unfulfilled.helper_level_4");
        appendBoolean(permissionManager.habboHasAccountPermission(habbo, "helper_use_guide_tool"));

        appendString("GIVE_GUIDE_TOURS");
        appendString("");
        appendBoolean(permissionManager.habboHasAccountPermission(habbo, "helper_give_guide_tours"));

        appendString("JUDGE_CHAT_REVIEWS");
        appendString("requirement.unfulfilled.helper_level_6");
        appendBoolean(permissionManager.habboHasAccountPermission(habbo, "helper_judge_chat_reviews"));

        appendString("VOTE_IN_COMPETITIONS");
        appendString("requirement.unfulfilled.helper_level_2");
        appendBoolean(true);

        appendString("CALL_ON_HELPERS");
        appendString("");
        appendBoolean(true);

        appendString("CITIZEN");
        appendString("");
        appendBoolean(true);

        appendString("TRADE");
        appendString("requirement.unfulfilled.no_trade_lock");
        appendBoolean(habbo.getSettings().allowTrade());

        appendString("HEIGHTMAP_EDITOR_BETA");
        appendString("requirement.unfulfilled.feature_disabled");
        appendBoolean(permissionManager.habboHasAccountPermission(habbo, "floorplan_editor"));

        appendString("BUILDER_AT_WORK");
        appendString("");
        appendBoolean(true);

        appendString("CALL_ON_HELPERS");
        appendString("");
        appendBoolean(true);

        appendString("CAMERA");
        appendString("");
        appendBoolean(permissionManager.habboHasAccountPermission(habbo, "camera"));

        appendString("NAVIGATOR_PHASE_TWO_2014");
        appendString("");
        appendBoolean(true);

        appendString("MOUSE_ZOOM");
        appendString("");
        appendBoolean(true);

        appendString("NAVIGATOR_ROOM_THUMBNAIL_CAMERA");
        appendString("");
        appendBoolean(true);

        appendString("HABBO_CLUB_OFFER_BETA");
        appendString("");
        appendBoolean(true);
    }
}
