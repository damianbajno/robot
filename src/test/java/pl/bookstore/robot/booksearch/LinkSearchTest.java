package pl.bookstore.robot.booksearch;

import com.jaunt.Document;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pl.bookstore.robot.hibernate.BookPersister;
import pl.bookstore.robot.pojo.BookStore;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Created by damian on 06.04.16.
 */
public class LinkSearchTest {

    @Test
    public void ifIPutDocumentWithLinksItFindLinks() throws ResponseException, NotFound{
        //given
        String expectedUrl="http://www.bookrix.com/_ebook-h-n-s-new-life/";
        BookStore bookStoreBookrix = new BookStore("bookrix", "http://www.bookrix.com/books.html", "<div class=\"item-content\">", "<a class=word-break>", "<ul class=item-details>" + "<li>");
        Document document= new UserAgent().openContent(this.getHtmlPage());
        LinkSearch linkSearch=new LinkSearch(bookStoreBookrix);

        //when
        HashSet<String> hyperLinks = linkSearch.searchHyperLinksOnPage(document);

        //then
        assertThat(hyperLinks.contains(expectedUrl)).isTrue();

    }

    @Test
    public void checkIfConnectionToPageIsSuccessful() {
        //given
        BookPersister bookPersister = new BookPersister();
        bookPersister.openSession();
        List<BookStore> bookStores = bookPersister.getBookStores();
        bookPersister.commitSession();

        for (BookStore bookStore : bookStores) {
            LinkSearch linkSearch = new LinkSearch(bookStore);

            Assertions.assertThat(linkSearch.visitPageAndGetDocument(bookStore.getUrl())).isNotNull();

        }
    }

    public String getHtmlPage() {
        String bookElement = new String("<a class=\"word-break\" href=\"http://www.bookrix.com/_ebook-h-n-s-new-life/\">New Life </a>" +
                "<a href=\"http://www.bookrix.com/search;keywords:romance,searchoption:books.html\">Romance</a>");
        return bookElement;
    }



}
