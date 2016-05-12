package pl.bookstore.robot.pojo;

import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by damian on 21.04.16.
 * Class contains all necessary fields and methods
 * associated with book profile creation
 * @author Fred
 * @version 1.0
 */

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> categories;
    @ManyToOne
    private BookStore bookStore;

    public Profile() {
        categories=new ArrayList<String>();
    }

    public boolean addCategory(String category) {
        return categories.add(category);
    }

    public void addCategories(ObservableList<String> categories) {
        this.categories.addAll(categories);
    }

    public void setBookStore(BookStore bookStore) {
        bookStore.addProfile(this);
        this.bookStore = bookStore;
    }

    public List<String> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (categories != null ? !categories.equals(profile.categories) : profile.categories != null) return false;
        return bookStore != null ? bookStore.equals(profile.bookStore) : profile.bookStore == null;

    }

    @Override
    public int hashCode() {
        int result = categories != null ? categories.hashCode() : 0;
        result = 31 * result + (bookStore != null ? bookStore.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return  "search by " + categories;
    }

}
