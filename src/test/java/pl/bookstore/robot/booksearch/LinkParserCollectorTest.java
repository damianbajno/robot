package pl.bookstore.robot.booksearch;

import com.jaunt.Document;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pl.bookstore.robot.dao.BookStoreDao;
import pl.bookstore.robot.pojo.BookStore;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by damian on 06.04.16.
 */
public class LinkParserCollectorTest {
//
//    @Test
//    public void ifIPutDocumentWithLinksItFindLinks() throws ResponseException, NotFound{
//        //given
//        String expectedUrl="http://www.bookrix.com/_ebook-h-n-s-new-life/";
//        BookStore bookStoreBookrix = new BookStore("bookrix", "http://www.bookrix.com/books.html", "<a class=word-break>", "<ul class=item-details>" + "<li>");
//        Document document= new UserAgent().openContent(this.getHtmlPage());
//        LinkParserCollector linkParserCollector =new LinkParserCollector(bookStoreBookrix);
//
//        //when
//        HashSet<String> hyperLinks = linkParserCollector.search(document);
//
//        //then
//        assertThat(hyperLinks.contains(expectedUrl)).isTrue();
//
//    }
//
//    @Test(enabled = false)
//    public void checkIfConnectionToPageIsSuccessful() {
//        //given
//        BookStoreDao bookStoreDao = new BookStoreDao();
//        List<BookStore> bookStores = bookStoreDao.getBookStoreList();
//
//        for (BookStore bookStore : bookStores) {
//            LinkParserCollector linkParserCollector = new LinkParserCollector(bookStore);
//
//            Assertions.assertThat(linkParserCollector.visitPageAndGetDocument(bookStore.getUrl())).isNotNull();
//
//        }
//    }

    public String getHtmlPage() {
        String bookElement = new String("<a class=\"word-break\" href=\"http://www.bookrix.com/_ebook-h-n-s-new-life/\">New Life </a>" +
                "<a href=\"http://www.bookrix.com/search;keywords:romance,searchoption:books.html\">Romance</a>");
        return bookElement;
    }



}
