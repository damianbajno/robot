package pl.bookstore.robot.utils;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

/**
 * Created by damian on 06.04.16.
 */
public class UrlUtilsTest {

    @Test
    public void retrieveMainPageUrlFromUrl(){
        //given
        String url="//demotywatory.pl/dsad";
        final String expectedUrl = "demotywatory.pl";

        //when
        String urlToMainPage = UrlUtils.getUrlToMainPage(url);
        //then
        Assertions.assertThat(urlToMainPage).isEqualTo(expectedUrl);
    }

    @Test
    public void retrieveMainPageUrlFromManePageUrl(){
        //given
        String url="demotywatory.pl";
        final String expectedUrl = "demotywatory.pl";

        //when
        String urlToMainPage = UrlUtils.getUrlToMainPage(url);
        //then
        Assertions.assertThat(urlToMainPage).isEqualTo(expectedUrl);
    }

}
