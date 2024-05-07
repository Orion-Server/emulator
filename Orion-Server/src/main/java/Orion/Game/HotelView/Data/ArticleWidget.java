package Orion.Game.HotelView.Data;

import Orion.Api.Server.Game.HotelView.Data.IArticleWidget;
import Orion.Api.Storage.Result.IConnectionResult;

public class ArticleWidget implements IArticleWidget {
    private int id;
    private String title;
    private String text;
    private String buttonText;
    private int type;
    private String link;
    private String image;

    public ArticleWidget(IConnectionResult data) {
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
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public String getButtonText() {
        return this.buttonText;
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public String getLink() {
        return this.link;
    }

    @Override
    public String getImage() {
        return this.image;
    }

    @Override
    public void fill(IConnectionResult data) throws Exception {
        this.id = data.getInt("id");
        this.title = data.getString("title");
        this.text = data.getString("text");
        this.buttonText = data.getString("button_text");
        this.type = data.getString("button_type").equals("client") ? 1 : 0;
        this.link = data.getString("button_link");
        this.image = data.getString("image");
    }
}
