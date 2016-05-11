package pl.bookstore.robot.utils;

import com.jaunt.HttpResponse;
import com.jaunt.UserAgent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which have methods to manage URL.
 * <p>
 * Created by damian on 21.03.16.
 * UrlUtils performs operations
 * associated with url handling.
 * i.e extracting website name, checking whether input string is url etc.
 *
 * @author Stycz
 * @version 1.0
 */

public class UrlUtils {

    /**
     * Retrieve url to main page from given string.
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
