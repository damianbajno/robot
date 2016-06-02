package pl.bookstore.robot.booksearch;

import com.jaunt.Document;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by damian on 6/2/16.
 */
public class DocumentWrapper {

    public static Document getDocumentFrom(String link) throws ResponseException {
        UserAgent userAgent = new UserAgent();
        return userAgent.visit(link);
    }

    static Document getDocumentFromResources(String bookStoreName) throws ResponseException, IOException {
        URL urlToFile = ClassLoader.getSystemResource("BookStorePages/" + bookStoreName + ".html");
        File bookStoreHtmlFile = new File(urlToFile.getFile());
        return new UserAgent().open(bookStoreHtmlFile);
    }

}
