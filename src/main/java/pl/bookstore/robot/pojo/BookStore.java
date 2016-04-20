package pl.bookstore.robot.pojo;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;


import pl.bookstore.robot.utils.UrlUtils;

import javax.persistence.*;

/**
 * Created by damian on 30.03.16.
 */

@Entity
@Table(name = "BookStore")
public class BookStore {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private String url;
    private String searchForElement;
    private String searchForTitle;
    private String searchForCategory;


    public BookStore(String name){this.name=name;}
    public BookStore(){}

    public BookStore(String name, String url, String searchForElement, String searchForTitle, String searchForCategory) {
        this.name = name;
        this.url = url;
        this.searchForElement = searchForElement;
        this.searchForTitle = searchForTitle;
        this.searchForCategory = searchForCategory;
    }

    public int getId() {
        return id;
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
                ", searchForElement='" + searchForElement + '\'' +
                ", searchForTitle='" + searchForTitle + '\'' +
                ", searchForCategory='" + searchForCategory + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearchForBook() {
        return searchForElement;
    }

    public void setSearchForElement(String searchForElement) {
        this.searchForElement = searchForElement;
    }

    public String getSearchForCategory() {
        return searchForCategory;
    }

    public void setSearchForCategory(String searchForCategory) {
        this.searchForCategory = searchForCategory;
    }

    public String getSearchForTitle() {
        return searchForTitle;
    }

    public void setSearchForTitle(String searchForTitle) {
        this.searchForTitle = searchForTitle;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookStore bookStore = (BookStore) o;

        if (id != bookStore.id) return false;
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
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (searchForElement != null ? searchForElement.hashCode() : 0);
        result = 31 * result + (searchForTitle != null ? searchForTitle.hashCode() : 0);
        result = 31 * result + (searchForCategory != null ? searchForCategory.hashCode() : 0);
        return result;
    }
}
