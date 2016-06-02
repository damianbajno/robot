package pl.bookstore.robot.booksearch;

import com.jaunt.Document;
import pl.bookstore.robot.pojo.Book;

import java.util.List;

/**
 * Created by damian on 6/1/16.
 */
//searchRecursivelyIn method to searchRecursivelyIn links and books
public interface BookParserWrapper {

    List<Book> searchIn(Document document);

}
