package pl.bookstore.robot.test;

import pl.bookstore.robot.DAO.BookDAO;
import pl.bookstore.robot.pojo.Book;

/**
 * Created by damian on 30.03.16.
 */
public class TestMainClass {

    public static void main(String[] args) {
        BookDAO bookDAO=new BookDAO();
        bookDAO.persist(new Book("Damian","PDsad"));
    }

}
