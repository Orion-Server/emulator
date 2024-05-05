package Orion.Protocol.Message.Event.Handshake;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Annotations.HandshakeEvent;
import Orion.Protocol.Message.Composer.GameCenter.GameAccountStatusComposer;
import Orion.Protocol.Message.Composer.GameCenter.GameListComposer;
import Orion.Protocol.Message.Composer.Handshake.*;
import Orion.Protocol.Message.Composer.LifeCycle.PingComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Hanshake.SSOTicketEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
@HandshakeEvent("SSOTicketEvent")
public class SSOTicketEvent implements IMessageEventHandler {
    @Inject
    private SSOTicketEventParser parser;

    @Override
    public int getId() {
        return EventHeaders.SSOTicketEvent;
    }

    @Override
    public IEventParser getParser() {
        return this.parser;
    }

    @Override
    public void handle(ISession session) {
        String ticket = this.parser.ticket;

        session.send(
            new AuthenticationOkComposer(), new AvatarEffectsComposer(), new FigureSetIdsComposer(), new NoobnessLevelComposer(), new UserRightsComposer(),
                new AvailabilityStatusComposer(), new PingComposer(), new InfoFeedEnableComposer(), new AchievementScoreComposer(),
                new IsFirstLoginOfDayComposer(), new MysteryBoxKeysComposer(), new ObjectsDataUpdateComposer(), new CfhTopicsInitComposer(), new FavouritesComposer(),
                //new GameListComposer(), new GameAccountStatusComposer(3, 100), new GameAccountStatusComposer(0, 100),
                new CampaignCalendarDataComposer(), new ScrSendUserInfoComposer(), new FurniListInvalidateComposer(), new InventoryAchievementsComposer(), new UserHomeRoomComposer()
        );
    }
}
