package pl.bookstore.robot.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by damian on 21.03.16.
 */
public class UrlUtils {

    public static boolean checkIfUrl(String url){
        if (url==null) return false;

        Pattern pattern=Pattern.compile("(http)?(s)?(://)?(www.)?[a-z0-9-]+[.][a-z]{1,5}(/)*.*");
        Matcher matcher = pattern.matcher(url);

        return matcher.matches();
    }

    public static String addHttpToBegining(String url){
           if (url.contains("https://") || url.contains("http://"))
               return url;
            return "https://" + url;
    }

    public static String getUrlToMainPage(String url){
        int urlBeginningIndex;
        int urlEndIndex;

        if ((urlBeginningIndex = (url.indexOf('/') + 2))==1) urlBeginningIndex=0;
        if ((urlEndIndex = url.indexOf('/', urlBeginningIndex))<4) urlEndIndex=url.length();
        return url.substring(urlBeginningIndex, urlEndIndex);
    }
}
