package pl.bookstore.robot.booksearch;

import com.jaunt.Document;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import org.testng.annotations.Test;
import pl.bookstore.robot.pojo.BookStore;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by damian on 06.04.16.
 */
public class    LinkSearchTest {

    @Test
    public void ifIPutDocumentWithLinksItFindLinks() throws ResponseException, NotFound{
        //given
        String expectedUrl="http://www.bookrix.com/_ebook-h-n-s-new-life/";
        BookStore bookStoreBoorix = new BookStore("boorix", "http://www.bookrix.com/books.html", "<div class=\"item-content\">", "<a class=word-break>", "<ul class=item-details>" + "<li>");
        Document document= new UserAgent().openContent(this.getHtmlPage());
        LinkSearch linkSearch=new LinkSearch(bookStoreBoorix);

        //when
        HashSet<String> hyperLinks = linkSearch.searchHyperLinksOnPage(document);

        //then
        assertThat(hyperLinks.contains(expectedUrl)).isTrue();

    }




    @Test(groups = "NewTest")
    public void checkIfConnectionToPageIsSuccessful() throws ResponseException{
        //given
        BookStore bookStoreBoorix = new BookStore("boorix", "http://www.bookrix.com/books.html", "<div class=\"item-content\">", "<a class=word-break>", "<ul class=item-details>" + "<li>");
        LinkSearch linkSearch=new LinkSearch(bookStoreBoorix);

        //when
        boolean connected = linkSearch.checkIfUrlIsValid();

        //then
        assertThat(connected).isTrue();
    }


    public String getHtmlPage() {
        String bookElement = new String("<a class=\"word-break\" href=\"http://www.bookrix.com/_ebook-h-n-s-new-life/\">New Life </a>" +
                "<a href=\"http://www.bookrix.com/search;keywords:romance,searchoption:books.html\">Romance</a>");
        return bookElement;
    }



}
