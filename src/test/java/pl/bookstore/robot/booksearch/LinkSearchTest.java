package pl.bookstore.robot.booksearch;

import com.jaunt.Document;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pl.bookstore.robot.pojo.BookStore;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by damian on 06.04.16.
 */
public class    LinkSearchTest {

    @Test
    public void ifRightLinkMachesCondition(){
        //given
        LinkSearch linkSearch=new LinkSearch(new BookStore("/demotywatory.pl/"));

        //when
        String hyperLink="demotywatory.pl";

        //then
        Assertions.assertThat(linkSearch.matchesConditions(hyperLink)).isTrue();

    }

    @Test(groups = "NewTest")
    public void ifIPutDocumentWithLinksItFindLinks() throws ResponseException, NotFound{
        //given
        BookStore bookStoreBoorix = new BookStore("boorix", "http://www.bookrix.com/books.html", "<div class=\"item-content\">", "<a class=word-break>", "<ul class=item-details>" + "<li>");
        Document document= new UserAgent().openContent(this.getHtmlPage());
        LinkSearch linkSearch=new LinkSearch(bookStoreBoorix);

        //when
        HashSet<String> hyperLinks = linkSearch.searchHyperLinkOnPage(document);

        //then
        assertThat(hyperLinks.size()).isGreaterThan(0);
        System.out.println(hyperLinks.toString());

    }

    public String getHtmlPage() {
        String bookElement = new String("<a class=\"word-break\" href=\"/_ebook-h-n-s-new-life/\">New Life </a>" +
                "<a href=\"http://www.bookrix.com/search;keywords:romance,searchoption:books.html\">Romance</a>");
        return bookElement;
    }


}
