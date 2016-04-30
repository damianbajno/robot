package pl.bookstore.robot.helper;

import com.jaunt.*;

/**
 * Created by damian on 4/30/16.
 */
public class TestClass {
    static String string="<body><ul class=\"details hidden-480 hidden-768 collapse-div\">Damian</div></body>";


    public static void main(String[] args) throws ResponseException,NotFound {
        Document document = new UserAgent().openContent(string);
        Elements every = document.findEvery("<ul class=details.*>");
        System.out.println("Size = "+every.size());
        System.out.println("Size = "+every.getElement(0).getText());


    }
}
