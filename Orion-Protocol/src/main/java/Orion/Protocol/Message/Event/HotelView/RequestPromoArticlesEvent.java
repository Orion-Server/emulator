package Orion.Protocol.Message.Event.HotelView;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Server.Game.HotelView.IHotelViewManager;
import Orion.Protocol.Message.Composer.HotelView.HallOfFameComposer;
import Orion.Protocol.Message.Composer.HotelView.HotelViewArticlesComposer;
import Orion.Protocol.Message.Composer.HotelView.HotelViewCampaignComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RequestPromoArticlesEvent implements IMessageEventHandler {
    @Inject
    private IHotelViewManager hotelViewManager;

    @Override
    public int getId() {
        return EventHeaders.RequestPromoArticlesEvent;
    }

    @Override
    public void handle(IMessageEvent event, ISession session) {
        session.send(new HotelViewCampaignComposer("2013-05-08 13:0", "gamesmaker"));
        session.send(new HallOfFameComposer(hotelViewManager.getHallOfFame()));
        session.send(new HotelViewArticlesComposer(hotelViewManager.getArticleWidgetList()));
    }
}
