package Orion.Game.Habbo.Provider;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Networking.Session.ISession;
import Orion.Api.Networking.Session.ISessionManager;
import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Api.Server.Game.Achievement.IAchievementManager;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Habbo.IHabboManager;
import Orion.Api.Server.Game.Habbo.Provider.IHabboLoginProvider;
import Orion.Api.Server.Task.IThreadManager;
import Orion.Api.Storage.Repository.Habbo.IHabboRepository;
import Orion.Game.Habbo.Factory.HabboFactory;
import Orion.Protocol.Message.Composer.Achievement.AchievementScoreComposer;
import Orion.Protocol.Message.Composer.Calendar.CampaignCalendarDataComposer;
import Orion.Protocol.Message.Composer.Habbo.Club.BuildersClubMembershipComposer;
import Orion.Protocol.Message.Composer.Habbo.Club.HabboClubComposer;
import Orion.Protocol.Message.Composer.Habbo.HabboHomeRoomComposer;
import Orion.Protocol.Message.Composer.Habbo.HabboNoobnessLevelComposer;
import Orion.Protocol.Message.Composer.Habbo.HabboRightsComposer;
import Orion.Protocol.Message.Composer.Habbo.Inventory.FigureSetIdsComposer;
import Orion.Protocol.Message.Composer.Habbo.Inventory.InventoryAchievementsComposer;
import Orion.Protocol.Message.Composer.Habbo.Inventory.InventoryEffectsListComposer;
import Orion.Protocol.Message.Composer.Habbo.Inventory.UpdateInventoryComposer;
import Orion.Protocol.Message.Composer.Habbo.IsFirstLoginOfDayComposer;
import Orion.Protocol.Message.Composer.Handshake.AuthenticationOkComposer;
import Orion.Protocol.Message.Composer.Handshake.AvailabilityStatusComposer;
import Orion.Protocol.Message.Composer.Handshake.NotificationsEnabledComposer;
import Orion.Protocol.Message.Composer.LifeCycle.PingComposer;
import Orion.Protocol.Message.Composer.Moderation.CfhTopicsInitComposer;
import Orion.Protocol.Message.Composer.MysteryBox.MysteryBoxKeysComposer;
import Orion.Protocol.Message.Composer.Room.FavoriteRoomsComposer;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class HabboLoginProvider implements IHabboLoginProvider {
    private final Logger logger = LogManager.getLogger();

    @Inject
    private IHabboRepository repository;

    @Inject
    private IHabboManager habboManager;

    @Inject
    private ISessionManager sessionManager;

    @Inject
    private HabboFactory habboFactory;

    @Inject
    private IEmulatorDatabaseSettings databaseSettings;

    @Inject
    private IAchievementManager achievementManager;

    @Inject
    private IThreadManager threadManager;

    @Override
    public boolean canLogin(final ISession session, String authTicket) {
        if(authTicket.isEmpty()) {
            return false;
        }

        final AtomicInteger habboId = new AtomicInteger(-1);

        this.repository.getHabboIdByAuthTicket(consumer -> {
            if(consumer == null) return;

            habboId.set(consumer.getInt("id"));
        }, authTicket);

        if(habboId.get() <= 0) {
            return false;
        }

        return !this.habboManager.hasLoggedHabboById(habboId.get());
    }

    @Override
    public void attemptLogin(final ISession session, String authTicket) {
        this.threadManager.getHabboLoginExecutor().execute(() -> this.repository.getHabboDataByAuthTicket(result -> {
            if(result == null) {
                this.sessionManager.disposeSession(session);
                return;
            }

            final IHabbo habbo = this.habboFactory.create(result);

            session.setHabbo(habbo);
            habbo.setSession(session);

            this.habboManager.addHabbo(habbo);

            this.sendLoginComposers(habbo);
        }, authTicket));
    }

    private void sendLoginComposers(final IHabbo habbo) {
        final ArrayList<IMessageComposer> composers = new ArrayList<>(19);

        composers.add(new AuthenticationOkComposer());
        composers.add(new InventoryEffectsListComposer(habbo));
        composers.add(new FigureSetIdsComposer(habbo));
        composers.add(new HabboNoobnessLevelComposer());
        composers.add(new HabboRightsComposer(habbo));
        composers.add(new AvailabilityStatusComposer(true, false, true));
        composers.add(new PingComposer());
        composers.add(new NotificationsEnabledComposer(this.databaseSettings));
        composers.add(new AchievementScoreComposer(habbo));
        composers.add(new IsFirstLoginOfDayComposer());
        composers.add(new MysteryBoxKeysComposer());
        composers.add(new BuildersClubMembershipComposer());
        composers.add(new CfhTopicsInitComposer());
        composers.add(new FavoriteRoomsComposer(habbo, this.databaseSettings));
        composers.add(new CampaignCalendarDataComposer());
        composers.add(new HabboClubComposer());
        composers.add(new UpdateInventoryComposer());
        composers.add(new InventoryAchievementsComposer(this.achievementManager));
        composers.add(new HabboHomeRoomComposer(habbo));

        habbo.getSession().send(composers);

        this.logger.info(STR."Habbo [\{habbo.getData().getUsername()}] has logged in.");
    }
}
