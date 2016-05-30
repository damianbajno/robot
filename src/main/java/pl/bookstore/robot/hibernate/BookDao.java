package pl.bookstore.robot.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import pl.bookstore.robot.pojo.Book;

import java.util.List;

/**
 * BookDao class created for saving data associated with books
 * of interest to database using Hibernate.
 *
 * @author Stycz
 * @version 1.0
 * @since 2016-04-14
 */
public class BookDao extends Dao {
    private Logger logger = Logger.getLogger(BookDao.class);

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
            bookList.forEach(book -> logger.trace("Book save in database = "+book.toString()));
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
     */
    public void persist(Book book) {
        try {
            beginTransaction();
            getSession().persist(book);
            commitTransaction();
            logger.trace("Book saved in database = "+book.toString());
        } catch (HibernateException e) {
            logger.warn("BookDao couldn't persist "+ book.toString());
            e.printStackTrace();
            rollback();
        }
    }
}

