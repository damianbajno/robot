package pl.bookstore.robot.bookssearch;

import pl.bookstore.robot.DAO.BookStoreDAO;
import pl.bookstore.robot.pojo.BookStore;

import java.util.List;

/**
 * Created by damian on 31.03.16.
 */
public class BookSearchEngine {

    public static void main(String[] args) {

        BookStoreDAO bookStoreDAO=new BookStoreDAO();
//        bookStoreDAO.persist(new BookStore("dasds"));
        final List<BookStore> bookStores = bookStoreDAO.getBookStores();

        System.out.println(bookStores.size());
        System.out.println(bookStores.get(0).toString());

//        LinkSearch linkSearch=new LinkSearch(bookStore);
//        linkSearch.searchHyperlinksOnSiteAndSubsites();

    }
}
