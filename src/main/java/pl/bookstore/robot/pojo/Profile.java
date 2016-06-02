package pl.bookstore.robot.pojo;

import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by damian on 21.04.16.
 *
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

    public Profile() {
        categories=new ArrayList<String>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean addCategory(String category) {
        return categories.add(category);
    }

    public void addCategories(ObservableList<String> categories) {
        this.categories.addAll(categories);
    }

    public List<String> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        return categories != null ? categories.equals(profile.categories) : profile.categories == null;

    }

    @Override
    public int hashCode() {
        return categories != null ? categories.hashCode() : 0;
    }

    @Override
    public String toString() {
        return  "searchRecursivelyIn by " + categories;
    }

}
