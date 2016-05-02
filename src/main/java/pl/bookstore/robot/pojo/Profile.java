package pl.bookstore.robot.pojo;

import pl.bookstore.robot.pojo.BookStore;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by damian on 21.04.16.
 */
@Entity
public class Profile {
    private String type;
    private ArrayList<String> values =new ArrayList<String>();
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BookStore bookStore;

    public Profile(String type) {
        this.type = type;
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

    public void addCategories(String[] categories) {
        for (int i = 0; i < categories.length; i++) {
            values.add(categories[i]);
        }
    }

    public void setBookStore(BookStore bookStore) {
        this.bookStore = bookStore;
    }
}
