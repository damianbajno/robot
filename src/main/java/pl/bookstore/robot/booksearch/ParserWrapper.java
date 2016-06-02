package pl.bookstore.robot.booksearch;

import com.jaunt.Document;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

/**
 * Created by damian on 6/1/16.
 */
public abstract class ParserWrapper {

    Document getDocument(String link) throws ResponseException {
        UserAgent userAgent = new UserAgent();
        return userAgent.visit(link);
    }




}
