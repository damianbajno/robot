package pl.bookstore.robot.booksearch;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pl.bookstore.robot.helper.BookStoreContainer;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.Arrays;
import java.util.List;

/**
 * Created by damian on 6/2/16.
 */
public class BookSearcherTest {


    @Test(groups = "IntegrationTestReallyLong")
    public void ifIPutValidLinkIReceiveListOfBooksFrom5Pages(){
        //given
        BookStore bookStore = BookStoreContainer.getBookStore(0);
        BookSearcher bookSearcher = new BookSearcher(bookStore);

        //when
        List<Book> bookList = bookSearcher.searchRecursivelyIn(Arrays.asList(bookStore.getUrl()));

        //then
        Assertions.assertThat(bookList.size()).isGreaterThan(0);
    }

}
