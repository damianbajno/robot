package pl.bookstore.robot.pojo;

import pl.bookstore.robot.utils.UrlUtils;

/**
 * Created by damian on 30.03.16.
 */
public class BookStore {
    private String name;
    private String url;
    private String searchForElement;
    private String searchForTitle;
    private String searchForCategory;

    public BookStore(String url) {
        this.url = url;
        this.name= UrlUtils.getUrlToMainPage(url);
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BookStore{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
