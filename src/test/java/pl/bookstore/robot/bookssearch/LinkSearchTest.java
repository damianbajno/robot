package pl.bookstore.robot.bookssearch;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pl.bookstore.robot.pojo.BookStore;

/**
 * Created by damian on 06.04.16.
 */
public class LinkSearchTest {

    @Test
    public void ifRightLinkMachesCondition(){
        //given
        LinkSearch linkSearch=new LinkSearch(new BookStore("/demotywatory.pl/"));

        //when
        String hyperLink="demotywatory.pl";

        //then
        Assertions.assertThat(linkSearch.matchesConditions(hyperLink)).isTrue();

    }

}
