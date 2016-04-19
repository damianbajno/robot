package pl.bookstore.robot.exercise;

import com.jaunt.Document;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

/**
 * Created by damian on 15.04.16.
 */
public class TestClass {
    public static void main(String[] args) {
        try {
            Document document = new UserAgent().visit("http://www.bookrix.com/search;keywords:parents");
        } catch (ResponseException e) {
            e.printStackTrace();
            System.out.println("Probelm");
        }

    }
}
