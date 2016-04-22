package pl.bookstore.robot.booksearch;

import java.util.ArrayList;

/**
 * Created by damian on 21.04.16.
 */
public class Profile {
    String type;
    ArrayList<String> values =new ArrayList<String>();

    public Profile(String type) {
        this.type = type;
    }

    public boolean add(String s) {
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
}
