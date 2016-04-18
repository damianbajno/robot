package pl.bookstore.robot.bookssearch;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.bookstore.robot.pojo.BookStore;

/**
 * Created by damian on 06.04.16.
 */
public class LinkSearchTest {



    @Test
    public void checkIfSearchingForHyperLinksWorks() throws NotFound, ResponseException {
        String link = "http://bonito.pl/";
        BookStore bookStore = new BookStore("Bonito",link,"<a href>",null, null);
        LinkSearch linkSearch = new LinkSearch(bookStore);
        Assert.assertEquals(linkSearch.searchHyperlinksOnSiteAndSubsites(), true);
    }


   /**
    @Test
    public void ifRightLinkMachesCondition(){
        //given
        LinkSearch linkSearch = new LinkSearch(new BookStore("/demotywatory.pl/"));

        //when
        String hyperLink="demotywatory.pl";

        //then
        Assertions.assertThat(linkSearch.matchesConditions(hyperLink)).isTrue();

    }
*/
}
