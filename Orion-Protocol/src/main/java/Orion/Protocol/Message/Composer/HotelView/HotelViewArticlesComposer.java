package Orion.Protocol.Message.Composer.HotelView;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.HotelView.Data.IArticleWidget;
import Orion.Api.Server.Game.HotelView.Data.IArticleWidgetList;
import Orion.Networking.Message.Composer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HotelViewArticlesComposer extends Composer {
    private final IArticleWidgetList articleWidgetList;

    public HotelViewArticlesComposer(final IArticleWidgetList articleWidgetList) {
        this.articleWidgetList = articleWidgetList;
    }

    @Override
    public short getId() {
        return ComposerHeaders.HotelViewArticlesComposer;
    }

    @Override
    public void compose(IMessageComposer msg) {
        msg.appendInt(this.articleWidgetList.getWidgets().size());

        for (final IArticleWidget articleWidget : this.articleWidgetList.getWidgets()) {
            msg.appendInt(articleWidget.getId());
            msg.appendString(articleWidget.getTitle());
            msg.appendString(articleWidget.getText());
            msg.appendString(articleWidget.getButtonText());
            msg.appendInt(articleWidget.getType());
            msg.appendString(articleWidget.getLink());
            msg.appendString(articleWidget.getImage());
        }
    }
}
