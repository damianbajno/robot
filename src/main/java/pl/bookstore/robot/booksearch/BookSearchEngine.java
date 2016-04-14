package pl.bookstore.robot.booksearch;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import pl.bookstore.robot.DAO.BookStoreDAO;
import pl.bookstore.robot.pojo.BookStore;

import java.util.List;

/**
 * Created by damian on 31.03.16.
 */
public class BookSearchEngine {

    public static void main(String[] args) {

        BookStoreDAO bookStoreDAO=new BookStoreDAO();
        List<BookStore> bookStores = bookStoreDAO.getBookStores();

        BookSearch bookSearch=new BookSearch(bookStores.get(0));
        try {
            bookSearch.searchBooksOnSite(bookStores.get(0).getUrl());
        } catch (ResponseException e) {
            e.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
    }
}
