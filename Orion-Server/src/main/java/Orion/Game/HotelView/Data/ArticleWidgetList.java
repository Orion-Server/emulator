package Orion.Game.HotelView.Data;

import Orion.Api.Server.Game.HotelView.Data.IArticleWidget;
import Orion.Api.Server.Game.HotelView.Data.IArticleWidgetList;

import java.util.ArrayList;
import java.util.List;

public class ArticleWidgetList implements IArticleWidgetList {
    private final List<IArticleWidget> widgets;

    public ArticleWidgetList() {
        this.widgets = new ArrayList<>();
    }

    @Override
    public List<IArticleWidget> getWidgets() {
        return this.widgets;
    }

    @Override
    public void setWidgets(List<IArticleWidget> widgets) {
        this.widgets.clear();
        this.widgets.addAll(widgets);
    }
}
