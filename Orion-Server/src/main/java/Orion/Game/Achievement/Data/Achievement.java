package Orion.Game.Achievement.Data;

import Orion.Api.Server.Game.Achievement.Data.IAchievement;
import Orion.Api.Server.Game.Achievement.Data.IAchievementLevel;
import Orion.Api.Server.Game.Achievement.Enum.AchievementCategory;
import Orion.Api.Server.Game.Habbo.Data.Achievement.IHabboAchievementProgress;
import Orion.Api.Storage.Result.IConnectionResult;
import gnu.trove.map.hash.THashMap;

public class Achievement implements IAchievement {
    private int id;
    private String name;
    private AchievementCategory category;
    private final THashMap<Integer, IAchievementLevel> levels;

    public Achievement(final IConnectionResult data) {
        this.levels = new THashMap<>();

        try {
            this.fill(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCompleteName(
            final IAchievementLevel currentProgress,
            final IAchievementLevel nextProgress
    ) {
        int level = 0;

        if(nextProgress != null) {
            level = nextProgress.getLevel();
        } else if(currentProgress != null) {
            level = currentProgress.getLevel();
        }

        return STR."ACH_\{this.name}\{level}";
    }

    @Override
    public AchievementCategory getCategory() {
        return this.category;
    }

    @Override
    public IAchievementLevel getLevel(int level) {
        return this.levels.get(level);
    }

    @Override
    public void addLevel(IAchievementLevel level) {
        this.levels.put(level.getLevel(), level);
    }

    @Override
    public THashMap<Integer, IAchievementLevel> getLevels() {
        return this.levels;
    }

    @Override
    public int getLevelsCount() {
        return this.levels.size();
    }

    @Override
    public void fill(IConnectionResult data) throws Exception {
        this.id = data.getInt("id");
        this.name = data.getString("name");
        this.category = AchievementCategory.valueOf(data.getString("category").toUpperCase());
    }
}
