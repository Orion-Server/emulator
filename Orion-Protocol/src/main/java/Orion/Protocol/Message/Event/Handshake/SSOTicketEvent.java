package Orion.Protocol.Message.Event.Handshake;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Protocol.Message.IMessageEventHandler;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Habbo.Provider.IHabboLoginProvider;
import Orion.Protocol.Annotations.HandshakeEvent;
import Orion.Protocol.Message.Composer.Achievement.AchievementScoreComposer;
import Orion.Protocol.Message.Composer.Calendar.CampaignCalendarDataComposer;
import Orion.Protocol.Message.Composer.Habbo.Club.BuildersClubMembershipComposer;
import Orion.Protocol.Message.Composer.Habbo.Club.HabboClubComposer;
import Orion.Protocol.Message.Composer.Habbo.HabboHomeRoomComposer;
import Orion.Protocol.Message.Composer.Habbo.HabboNoobnessLevelComposer;
import Orion.Protocol.Message.Composer.Habbo.HabboRightsComposer;
import Orion.Protocol.Message.Composer.Habbo.Inventory.FigureSetIdsComposer;
import Orion.Protocol.Message.Composer.Habbo.Inventory.InventoryEffectsListComposer;
import Orion.Protocol.Message.Composer.Habbo.Inventory.InventoryAchievementsComposer;
import Orion.Protocol.Message.Composer.Habbo.Inventory.UpdateInventoryComposer;
import Orion.Protocol.Message.Composer.Habbo.IsFirstLoginOfDayComposer;
import Orion.Protocol.Message.Composer.Handshake.*;
import Orion.Protocol.Message.Composer.LifeCycle.PingComposer;
import Orion.Protocol.Message.Composer.Moderation.CfhTopicsInitComposer;
import Orion.Protocol.Message.Composer.MysteryBox.MysteryBoxKeysComposer;
import Orion.Protocol.Message.Composer.Room.FavoriteRoomsComposer;
import Orion.Protocol.Message.Event.EventHeaders;
import Orion.Protocol.Parser.Hanshake.SSOTicketEventParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
@HandshakeEvent("SSOTicketEvent")
public class SSOTicketEvent implements IMessageEventHandler {
    @Inject
    private SSOTicketEventParser parser;

    @Inject
    private IHabboLoginProvider loginProvider;

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
        if(!this.loginProvider.canLogin(session, this.parser.ticket)) return;

        this.loginProvider.attemptLogin(session, this.parser.ticket);

//        session.send(
//            new AuthenticationOkComposer(), new InventoryEffectsListComposer(), new FigureSetIdsComposer(), new HabboNoobnessLevelComposer(), new HabboRightsComposer(),
//                new AvailabilityStatusComposer(), new PingComposer(), new NotificationsEnabledComposer(), new AchievementScoreComposer(),
//                new IsFirstLoginOfDayComposer(), new MysteryBoxKeysComposer(), new BuildersClubMembershipComposer(), new CfhTopicsInitComposer(), new FavoriteRoomsComposer(),
//                //new GameListComposer(), new GameAccountStatusComposer(3, 100), new GameAccountStatusComposer(0, 100),
//                new CampaignCalendarDataComposer(), new HabboClubComposer(), new UpdateInventoryComposer(), new InventoryAchievementsComposer(), new HabboHomeRoomComposer()
//        );
    }
}
