package Orion.Protocol.Message.Composer.Navigator;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class NavigatorCollapsedCategoriesComposer extends Composer {
    @Override
    public short getId() {
        return ComposerHeaders.NavigatorCollapsedCategoriesComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(46);

        msg.appendString("new_ads");
        msg.appendString("friend_finding");
        msg.appendString("staffpicks");
        msg.appendString("with_friends");
        msg.appendString("with_rights");
        msg.appendString("query");
        msg.appendString("recommended");
        msg.appendString("my_groups");
        msg.appendString("favorites");
        msg.appendString("history");
        msg.appendString("top_promotions");
        msg.appendString("campaign_target");
        msg.appendString("friends_rooms");
        msg.appendString("groups");
        msg.appendString("metadata");
        msg.appendString("history_freq");
        msg.appendString("highest_score");
        msg.appendString("competition");
        msg.appendString("category__Agencies");
        msg.appendString("category__Role Playing");
        msg.appendString("category__Global Chat & Discussi");
        msg.appendString("category__GLOBAL BUILDING AND DE");
        msg.appendString("category__global party");
        msg.appendString("category__global games");
        msg.appendString("category__global fansite");
        msg.appendString("category__global help");
        msg.appendString("category__Trading");
        msg.appendString("category__global personal space");
        msg.appendString("category__Habbo Life");
        msg.appendString("category__TRADING");
        msg.appendString("category__global official");
        msg.appendString("category__global trade");
        msg.appendString("category__global reviews");
        msg.appendString("category__global bc");
        msg.appendString("category__global personal space");
        msg.appendString("eventcategory__Hottest Events");
        msg.appendString("eventcategory__Parties & Music");
        msg.appendString("eventcategory__Role Play");
        msg.appendString("eventcategory__Help Desk");
        msg.appendString("eventcategory__Trading");
        msg.appendString("eventcategory__Games");
        msg.appendString("eventcategory__Debates & Discuss");
        msg.appendString("eventcategory__Grand Openings");
        msg.appendString("eventcategory__Friending");
        msg.appendString("eventcategory__Jobs");
        msg.appendString("eventcategory__Group Events");
    }
}