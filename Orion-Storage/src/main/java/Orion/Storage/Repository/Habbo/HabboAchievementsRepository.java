package Orion.Storage.Repository.Habbo;

import Orion.Api.Storage.Repository.Habbo.IHabboAchievementsRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.Habbo.HabboAchievementsQuery;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Singleton;

@Singleton
public class HabboAchievementsRepository extends DatabaseRepository implements IHabboAchievementsRepository {
    public void loadAllAchievementsByHabboId(IConnectionResultConsumer consumer, int habboId) {
        this.select(HabboAchievementsQuery.LOAD_ALL_ACHIEVEMENTS.get(), consumer, habboId);
    }
}
