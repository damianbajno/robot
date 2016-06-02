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

    @Test(groups = "IntegrationTestRealyLong")
    public void ifIPutValidLinkIReceiveListOfBooksFrom5Pages(){
        //given
        BookStore bookStore = BookStoreContainer.getBookStore(0);
        BookSearcher bookSearcher = new BookSearcher(bookStore);

        //when
        List<Book> bookList = bookSearcher.searchRecursivelyIn(Arrays.asList(bookStore.getUrl()));

        //then
        Assertions.assertThat(bookList.size()).isGreaterThan(0);
    }

    @Test
    public void ifIPutNotValidLinkIReceiveListOfBooksSize0(){
        //given
        BookStore bookStore = BookStoreContainer.getBookStore(0);
        BookSearcher bookSearcher = new BookSearcher(bookStore);
        List<String> givenLinks = Arrays.asList("dsdsa");

        //when
        List<Book> bookList = bookSearcher.searchRecursivelyIn(givenLinks);

        //then
        Assertions.assertThat(bookList.size()).isEqualTo(0);
    }

    @Test
    public void ifIPutTwoSameLinksIReceiveListOfBooksSizeGreaterThen0(){
        //given
        BookStore bookStore = BookStoreContainer.getBookStore(0);
        BookSearcher bookSearcher = new BookSearcher(bookStore);
        List<String> givenLinks = Arrays.asList("dsdsa");
        List<String> givenLinkList = Arrays.asList(bookStore.getUrl(), bookStore.getUrl());

        //when
        List<Book> bookList = bookSearcher.searchRecursivelyIn(givenLinkList);

        //then
        Assertions.assertThat(bookList.size()).isEqualTo(0);
    }
}
