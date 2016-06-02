package pl.bookstore.robot.booksearch.wrapper;

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
public class BookParserWrapperTest {

    @DataProvider()
    public Object[][] dataProviderForSearchBookInDocument() {
        BookStore[] bookStores = BookStoreContainer.getBookStores();

        Object[][] data = {
                {bookStores[0], new Book("New Life", "Romance")},
                {bookStores[1], new Book("Going Viral : The 9 Secrets of Irresistible Marketing", "brak")},
                {bookStores[2], new Book("Inwestycje", "INWESTYCJE")}
        };
        return data;
    }

    @Test(dataProvider = "dataProviderForSearchBookInDocument")
    public void ifIPutDocumentWithBookItRetrieveBook(BookStore bookStore, Book bookExpected) throws NotFound, ResponseException, IOException{
        //given
        Document documentFromResources = DocumentWrapper.getDocumentFromResources(bookStore.getName());
        BookParserWrapper bookParserWrapper = new BookParserWrapper(bookStore);

        //when
        List<Book> books = bookParserWrapper.search(documentFromResources);

        //then
        Assertions.assertThat(books.size()).isEqualTo(1);
        Assertions.assertThat(books).contains(bookExpected);
    }

}
