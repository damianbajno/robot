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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookStore bookStore = (BookStore) o;

        if (name != null ? !name.equals(bookStore.name) : bookStore.name != null) return false;
        if (url != null ? !url.equals(bookStore.url) : bookStore.url != null) return false;
        if (searchForElement != null ? !searchForElement.equals(bookStore.searchForElement) : bookStore.searchForElement != null)
            return false;
        if (searchForTitle != null ? !searchForTitle.equals(bookStore.searchForTitle) : bookStore.searchForTitle != null)
            return false;
        return searchForCategory != null ? searchForCategory.equals(bookStore.searchForCategory) : bookStore.searchForCategory == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (searchForElement != null ? searchForElement.hashCode() : 0);
        result = 31 * result + (searchForTitle != null ? searchForTitle.hashCode() : 0);
        result = 31 * result + (searchForCategory != null ? searchForCategory.hashCode() : 0);
        return result;
    }
}
