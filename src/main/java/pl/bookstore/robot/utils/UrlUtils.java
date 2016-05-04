package pl.bookstore.robot.utils;

import com.jaunt.HttpResponse;
import com.jaunt.UserAgent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Class which have methods to manage URL.
 *
 * Created by damian on 21.03.16.
 */
public class UrlUtils {

    /**
     *
     * Method check if given string is url by matching pattern.
     *
     * @param url
     * @return boolean
     */

    public static boolean checkIfUrl(String url){
        if (url==null) return false;

        Pattern pattern=Pattern.compile("(http)?(s)?(://)?(www.)?[a-z0-9-]+[.][a-z]{1,5}(/)*.*");
        Matcher matcher = pattern.matcher(url);

        return matcher.matches();
    }

    /**
     *
     * Retrieve url to main page from given string.
     *
     * @param url
     * @return retrieving url to main page
     */

    public static String getUrlToMainPage(String url){
        int urlBeginningIndex;
        int urlEndIndex;

        if ((urlBeginningIndex = (url.indexOf('/') + 2))==1) urlBeginningIndex=0;
        if ((urlEndIndex = url.indexOf('/', urlBeginningIndex))<4) urlEndIndex=url.length();
        return url.substring(urlBeginningIndex, urlEndIndex);
    }
}
