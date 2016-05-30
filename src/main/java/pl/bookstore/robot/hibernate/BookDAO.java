package pl.bookstore.robot.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;
import pl.bookstore.robot.pojo.Profile;

import java.util.List;

/**
 * BookDAO class created for saving data associated with books
 * of interest to database using Hibernate.
 *
 * @author Stycz
 * @version 1.0
 * @since 2016-04-14
 */

public class BookDAO extends DAO {
    private Logger logger = Logger.getLogger(BookDAO.class);

    /**
     * Save list of books in database
     *
     * @param bookList to save in database
     */

    public void persistBookList(List<Book> bookList) {
        try {
            beginTransaction();
            bookList.forEach(book -> getSession().persist(book));
            commitTransaction();
        } catch (HibernateException e) {
            logger.warn("BookDao couldn't persist BookList");
            e.printStackTrace();
            rollback();
        }
    }

    /**
     * Save book in database
     *
     * @param book to save in database
     * @return if saved return true
     */

    public void persist(Book book) {
        try {
            beginTransaction();
            getSession().persist(book);
            commitTransaction();
        } catch (HibernateException e) {
            logger.warn("BookDao couldn't persist "+ book.toString());
            e.printStackTrace();
            rollback();
        }
    }

}

