package pl.bookstore.robot.DAO;

import org.apache.log4j.Logger;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by damian on 30.03.16.
 */
public class ResultSetConverter {
    private static Logger logger = org.apache.log4j.Logger.getLogger(ResultSetConverter.class);

    public static List<Book> convertToBook(ResultSet resultSet) {
        List<Book> books = new ArrayList<Book>();
        try {
            while (resultSet.next()) {
                Book book = new Book(resultSet.getString(1), resultSet.getString(2));
                books.add(book);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return books;
    }

    public static List<BookStore> convertToBookStore(ResultSet resultSet) {
        logger.info("Converting from resoultset to BookStore");
        List<BookStore> bookStores = new ArrayList<BookStore>();
        try {
            while (resultSet.next()) {
                BookStore bookStore = new BookStore(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                bookStores.add(bookStore);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return bookStores;
    }
}
