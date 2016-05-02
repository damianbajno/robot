package pl.bookstore.robot.pojo;

import javafx.collections.ObservableList;
import pl.bookstore.robot.pojo.BookStore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by damian on 21.04.16.
 */
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    @ElementCollection
    private List<String> values;
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private BookStore bookStore;

    public Profile(String type) {
        this.type = type;
        values =new ArrayList<String>();
    }

    public boolean addCategory(String s) {
        return values.add(s);
    }

    public String remove(int index) {
        return values.remove(index);
    }

    public void clear() {
        values.clear();
    }

    public boolean remove(Object o) {
        return values.remove(o);
    }

    @Override
    public String toString() {
        return  type + " search by=" + values ;
    }

    public void addCategories(ObservableList<String> categories) {
        values.addAll(categories);
    }

    public void addCategories(String[] categories) {
        for (int i = 0; i < categories.length; i++) {
            values.add(categories[i]);
        }
    }

//    public void setBookStore(BookStore bookStore) {
//        this.bookStore = bookStore;
//    }
}
