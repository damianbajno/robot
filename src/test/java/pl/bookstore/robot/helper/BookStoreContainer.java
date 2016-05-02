package pl.bookstore.robot.helper;

import pl.bookstore.robot.pojo.BookStore;

import java.util.Arrays;
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
        bookStoreBookrix.setId(1);
        BookStore bookStoreGoodreads = new BookStore("goodreads", "https://www.goodreads.com/genres/business", "<div class=description descriptionContainer>", "<a class=bookTitle>", "<brak");
        bookStoreGoodreads.setId(2);
        BookStore bookStorePwn = new BookStore("Pwn", "http://ksiegarnia.pwn.pl/Inwestycje,68736511,p.html", "<span class=name itemprop=itemReviewed>", "<ul class=details.*>2<li><h3>2<span><a>");
        bookStoreGoodreads.setId(3);

        bookStoreHashMap.put(bookStoreBookrix.getName(), bookStoreBookrix);
        bookStoreHashMap.put(bookStoreGoodreads.getName(), bookStoreGoodreads);
        bookStoreHashMap.put(bookStorePwn.getName(), bookStorePwn);

    }

    public static BookStore getBookStore(int number){
        Collection<BookStore> values = bookStoreHashMap.values();
        return (BookStore) values.toArray()[number];
    }

    public static BookStore getBookStore(String bookStoreName){
        return bookStoreHashMap.get(bookStoreName);
    }


    public static BookStore[] getBookStores() {
        Object[] objectArray = bookStoreHashMap.values().toArray();
        BookStore[] bookStoreArray=new BookStore[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            bookStoreArray[i]=(BookStore) objectArray[i];
        }
        return bookStoreArray;
    }
}

