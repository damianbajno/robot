package pl.bookstore.robot.booksearch;

import com.jaunt.Document;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.bookstore.robot.helper.BookStoreContainer;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.io.IOException;
import java.util.List;

/**
 * Created by damian on 6/1/16.
 */
public class BookBookParserWrapperTest {

    @DataProvider()
    public Object[][] dataProviderForSearchBookInDocument() {
        BookStore[] bookStores = BookStoreContainer.getBookStores();

        Object[][] data = {
                {bookStores[0], new Book("New Life", "Romance")},
                {bookStores[1], new Book("Write in Me 6: Personal Journal 160 Pages Ruled Mandala", "brak")},
                {bookStores[2], new Book("Inwestycje", "INWESTYCJE")}
        };
        return data;
    }

    @Test(dataProvider = "dataProviderForSearchBookInDocument")
    public void ifIPutDocumentWithBookItRetrieveBook(BookStore bookStore, Book bookExpected) throws NotFound, ResponseException, IOException{
        //given
        Document documentFromResources = DocumentWrapper.getDocumentFromResources(bookStore.getName());
        BookParserWrapperImpl bookParserWrapperImpl = new BookParserWrapperImpl(bookStore);

        //when
        List<Book> books = bookParserWrapperImpl.searchIn(documentFromResources);

        //then
        Assertions.assertThat(books.size()).isEqualTo(1);
        Assertions.assertThat(books).contains(bookExpected);
    }

}
