package pl.bookstore.robot.helper;

import pl.bookstore.robot.pojo.BookStore;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by damian on 4/28/16.
 */
public class BookStoreContainer {
    private final static LinkedHashMap<String, BookStore> bookStoreHashMap = new LinkedHashMap();

    static {
        BookStore bookStoreBookrix = new BookStore("bookrix", "http://www.bookrix.com/books.html", "<div class=item-content>", "<a class=word-break>", "<ul class=item-details><li>");
        BookStore bookStoreGoodreads = new BookStore("goodreads", "https://www.goodreads.com/genres/business", "<div class=description descriptionContainer>", "<a class=bookTitle>", "brak");
        BookStore bookStoreGutenberg = new BookStore("gutenberg", "https://www.gutenberg.org/ebooks/searchBooks/?query=free+book&go=Go", "<div class=header>", "<h1 itemprop=name>", "brak");

        bookStoreHashMap.put(bookStoreBookrix.getName(), bookStoreBookrix);
        bookStoreHashMap.put(bookStoreGoodreads.getName(), bookStoreGoodreads);
        bookStoreHashMap.put(bookStoreGutenberg.getName(), bookStoreGutenberg);
    }

    public static BookStore getBookStore(int number){
        Collection<BookStore> values = bookStoreHashMap.values();
        return (BookStore) values.toArray()[number];
    }

    public static BookStore getBookStore(String bookStoreName){
        return bookStoreHashMap.get(bookStoreName);
    }


    public static BookStore[] getBookStores() {
        return (BookStore[]) bookStoreHashMap.values().toArray();
    }
}

