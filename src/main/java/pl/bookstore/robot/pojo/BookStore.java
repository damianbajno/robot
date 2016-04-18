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

    public BookStore(){}

    public BookStore(String name, String url, String searchForElement, String searchForTitle, String searchForCategory) {
        this.name = name;
        this.url = url;
        this.searchForElement = searchForElement;
        this.searchForTitle = searchForTitle;
        this.searchForCategory = searchForCategory;
    }
    public BookStore(String name){
        this.name = name;
    }
    /**
     * @info
     * Getters and setters created for hibernate
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearchForElement() {
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

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
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
