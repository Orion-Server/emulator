package Orion.Storage.Repository.Achievement;

import Orion.Api.Storage.Repository.Achievement.IAchievementRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.Achievement.AchievementQuery;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Singleton;

@Singleton
public class AchievementRepository extends DatabaseRepository implements IAchievementRepository {
    public void loadAllAchievements(IConnectionResultConsumer consumer) {
        this.select(AchievementQuery.LOAD_ALL_ACHIEVEMENTS.get(), consumer);
    }
}
