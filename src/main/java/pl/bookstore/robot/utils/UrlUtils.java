package pl.bookstore.robot.utils;

/**
 * Retrieve url to main page from given url.
 * <p>
 * Created by damian on 21.03.16.
 *
 * @author Stycz
 * @version 1.0
 */

public class UrlUtils {

    /**
     * Retrieve url to main page from given string url.
     *
     * @param url given url to sub page
     * @return retrieving url to main page
     */
    public static String getUrlToMainPage(String url) {
        int urlBeginningIndex;
        int urlEndIndex;

        if ((urlBeginningIndex = (url.indexOf('/') + 2)) == 1) urlBeginningIndex = 0;
        if ((urlEndIndex = url.indexOf('/', urlBeginningIndex)) < 4) urlEndIndex = url.length();
        return url.substring(urlBeginningIndex, urlEndIndex);
    }
}
