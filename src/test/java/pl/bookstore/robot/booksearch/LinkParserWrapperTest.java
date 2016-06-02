package pl.bookstore.robot.booksearch;

import com.jaunt.Document;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import org.testng.annotations.Test;
import pl.bookstore.robot.helper.BookStoreContainer;
import pl.bookstore.robot.pojo.BookStore;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by damian on 6/1/16.
 */
public class LinkParserWrapperTest {

    @Test
    public void ifIPutDocumentWithLinksItFindLinks() throws ResponseException {
        //given
        String expectedUrl="http://www.bookrix.com/_ebook-h-n-s-new-life/";
        BookStore bookStoreBookrix = BookStoreContainer.getBookStore(0);
        Document document= new UserAgent().openContent(this.getHtmlPage());
        LinkParserWrapper linkParserWrapper =new LinkParserWrapper(bookStoreBookrix);

        //when
        List<String> hyperLinks = linkParserWrapper.searchIn(document);

        //then
        assertThat(hyperLinks.contains(expectedUrl)).isTrue();

    }

    @Test
    public void ifIPutDocumentWithOutLinksItWillReturnListWithSize0() throws ResponseException {
        //given
        String expectedUrl="http://www.bookrix.com/_ebook-h-n-s-new-life/";
        BookStore bookStoreBookrix = BookStoreContainer.getBookStore(0);
        Document document= new UserAgent().openContent(new String());
        LinkParserWrapper linkParserWrapper =new LinkParserWrapper(bookStoreBookrix);

        //when
        List<String> hyperLinks = linkParserWrapper.searchIn(document);

        //then
        assertThat(hyperLinks.size()).isEqualTo(0);

    }

    public String getHtmlPage() {
        String bookElement = new String("<a class=\"word-break\" href=\"http://www.bookrix.com/_ebook-h-n-s-new-life/\">New Life </a>" +
                "<a href=\"http://www.bookrix.com/searchRecursivelyIn;keywords:romance,searchoption:books.html\">Romance</a>");
        return bookElement;
    }
}
