package pl.bookstore.robot.helper;

import com.jaunt.*;
import pl.bookstore.robot.pojo.Book;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * Created by damian on 4/30/16.
 */
public class TestClass {

    public static void main(String[] args) {
        ArrayList<Book> bookArrayList=new ArrayList<>();
        Book[] books={new Book("a1", "b1"), new Book("a2", "b2"), new Book("a3", "b2")};

        bookArrayList.addAll(Arrays.asList(books));

        bookArrayList.stream().map(book -> book.getCategory()).distinct().forEach(e -> System.out.println(e));


    }
}
