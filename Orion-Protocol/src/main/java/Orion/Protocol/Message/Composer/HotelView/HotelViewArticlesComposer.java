package Orion.Protocol.Message.Composer.HotelView;

import Orion.Api.Server.Game.HotelView.Data.IArticleWidget;
import Orion.Api.Server.Game.HotelView.Data.IArticleWidgetList;
import Orion.Networking.Message.MessageComposer;
import Orion.Protocol.Message.Composer.ComposerHeaders;

public class HotelViewArticlesComposer extends MessageComposer {
    public HotelViewArticlesComposer(final IArticleWidgetList articleWidgetList) {
        super(ComposerHeaders.HotelViewArticlesComposer);

        appendInt(articleWidgetList.getWidgets().size());

        for (final IArticleWidget articleWidget : articleWidgetList.getWidgets()) {
            appendInt(articleWidget.getId());
            appendString(articleWidget.getTitle());
            appendString(articleWidget.getText());
            appendString(articleWidget.getButtonText());
            appendInt(articleWidget.getType());
            appendString(articleWidget.getLink());
            appendString(articleWidget.getImage());
        }
    }
}
