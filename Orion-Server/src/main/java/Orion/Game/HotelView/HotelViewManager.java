package Orion.Game.HotelView;

import Orion.Api.Server.Game.HotelView.Data.IArticleWidget;
import Orion.Api.Server.Game.HotelView.Data.IArticleWidgetList;
import Orion.Api.Server.Game.HotelView.Data.IHallOfFame;
import Orion.Api.Server.Game.HotelView.Data.IHallOfFameWinner;
import Orion.Api.Server.Game.HotelView.IHotelViewManager;
import Orion.Api.Storage.Repository.HotelView.IHotelViewRepository;
import Orion.Game.HotelView.Data.ArticleWidget;
import Orion.Game.HotelView.Data.ArticleWidgetList;
import Orion.Game.HotelView.Data.HallOfFame;
import Orion.Game.HotelView.Data.HallOfFameWinner;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
public class HotelViewManager implements IHotelViewManager {
    private final Logger logger = LogManager.getLogger();

    private final IHallOfFame hallOfFame;

    private final IArticleWidgetList articleWidgetList;

    @Inject
    private IHotelViewRepository repository;

    public HotelViewManager() {
        this.hallOfFame = new HallOfFame();
        this.articleWidgetList = new ArticleWidgetList();
    }

    @Override
    public IHallOfFame getHallOfFame() {
        return this.hallOfFame;
    }

    @Override
    public IArticleWidgetList getArticleWidgetList() {
        return this.articleWidgetList;
    }

    @Override
    public void initialize() {
        this.loadHallOfFameWinners();
        this.loadArticleWidgetList();

        this.logger.debug(STR."[\{this.hallOfFame.getHabboWinners().size()}] hall of fame winners loaded successfully.");
        this.logger.debug(STR."[\{this.articleWidgetList.getWidgets().size()}] article widgets loaded successfully.");
    }

    private void loadHallOfFameWinners() {
        final List<IHallOfFameWinner> habboWinners = new ArrayList<>();

        this.repository.loadHallOfFameWinners(result -> {
            if(result == null) return;

            habboWinners.add(new HallOfFameWinner(result));
        });

        Collections.sort(habboWinners);

        this.hallOfFame.setHabboWinners(habboWinners);
    }

    private void loadArticleWidgetList() {
        final List<IArticleWidget> articleWidgets = new ArrayList<>();

        this.repository.loadHotelViewArticles(result -> {
            if(result == null) return;

            articleWidgets.add(new ArticleWidget(result));
        });

        this.articleWidgetList.setWidgets(articleWidgets);
    }
}
