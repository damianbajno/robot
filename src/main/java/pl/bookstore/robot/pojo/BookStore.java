package pl.bookstore.robot.pojo;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by damian on 30.03.16.
 */

@Entity
public class BookStore {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private String url;
    private String searchForBook;
    private String searchForTitle;
    private String searchForCategory;

    public BookStore(String name){this.name=name;}

    public BookStore(){
        this.name = "brak";
        this.url = "";
        this.searchForBook = "";
        this.searchForTitle = "";
        this.searchForCategory = "";
    }

    public BookStore(String name, String url, String searchForBook, String searchForTitle, String searchForCategory) {
        this.name = name;
        this.url = url;
        this.searchForBook = searchForBook;
        this.searchForTitle = searchForTitle;
        this.searchForCategory = searchForCategory;
    }

    public BookStore(String name, String url, String searchForTitle, String searchForCategory) {
        this.name = name;
        this.url = url;
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
                ", searchForElement='" + searchForBook + '\'' +
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
        return searchForBook;
    }

    public void setSearchForBook(String searchForBook) {
        this.searchForBook = searchForBook;
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
        if (searchForBook != null ? !searchForBook.equals(bookStore.searchForBook) : bookStore.searchForBook != null)
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
        result = 31 * result + (searchForBook != null ? searchForBook.hashCode() : 0);
        result = 31 * result + (searchForTitle != null ? searchForTitle.hashCode() : 0);
        result = 31 * result + (searchForCategory != null ? searchForCategory.hashCode() : 0);
        return result;
    }
}
