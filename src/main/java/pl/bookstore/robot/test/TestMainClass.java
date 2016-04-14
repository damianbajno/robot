package pl.bookstore.robot.test;

import pl.bookstore.robot.DAO.BookStoreDAO;
import pl.bookstore.robot.pojo.BookStore;

import java.util.Iterator;
import java.util.List;

/**
 * Created by damian on 30.03.16.
 */
public class TestMainClass {

    public static void main(String[] args) {
        printDatabasesBookStores();
    }

    public static void sendBookStoreToDatabases() {
        BookStore bookStoreBoorix = new BookStore("boorix", "http://www.bookrix.com/books.html", "<div class=item-content>", "<a class=word-break>", "<ul class=item-details>" + "<li>");
        BookStore bookStoreGoodreads = new BookStore("goodreads", "https://www.goodreads.com/genres/business", "<div class=description descriptionContainer>", "<a class=bookTitle>", "brak");
        BookStore bookStoreGutenberg = new BookStore("gutenberg", "https://www.gutenberg.org/ebooks/search/?query=free+book&go=Go", "<div class=header>", "<h1 itemprop=name>", "brak");

        BookStoreDAO bookStoreDAO = new BookStoreDAO();
        bookStoreDAO.persist(bookStoreBoorix);
        bookStoreDAO.persist(bookStoreGoodreads);
        bookStoreDAO.persist(bookStoreGutenberg);
    }

    public static void printDatabasesBookStores() {
        BookStoreDAO bookStoreDAO = new BookStoreDAO();
        List<BookStore> bookStores = bookStoreDAO.getBookStores();

        Iterator<BookStore> iterator = bookStores.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }

}
