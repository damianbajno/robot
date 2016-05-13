package pl.bookstore.robot.hibernate;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.List;

/**
 * BookPersister class created for saving data associated with books
 * of interest to database using Hibernate.
 * @author  Stycz
 * @version 1.0
 * @since   2016-04-14
 */

public class BookPersister extends HibernateUtil {
     Logger logger = Logger.getLogger(BookPersister.class);

    /**
     * persistBookStore method to save particular bookstore in database
     * @param bookStore object to save in database
     * @return true if bookstore is saved successfully
     * @param bookStore is a instance of BookStore class
     */

    public boolean persistBookStore(BookStore bookStore) {
        session.persist(bookStore);
        logger.info("Book store saved in database");

        return true;
    }

    /**
     * updateBookStore method to update particular bookstore in database
     * @param bookStore object will be updated in database
     * @return true if database os updated successfully
     * @param bookStore is a instance of BookStore class
     */
    public boolean updateBookStore(BookStore bookStore){
        session.update(bookStore);
        logger.info("Book store updated");

        return true;
    }


    /**
     * Retrieve all BookStores from database
     *
     * @return List of BookStores
     */
    public List<BookStore> getBookStores(){
        Query getBookStoreQuery = session.createQuery("from "+BookStore.class.getSimpleName());
        List<BookStore> bookStoreList = getBookStoreQuery.list();
        return bookStoreList;
    }

    /**
     * Save list of books in database
     *
     * @param bookList to save in database
     * @return if saved return true
     */

    public boolean persistBooks(List<Book> bookList) {
        bookList.forEach(book -> session.persist(book));
        logger.info("Book saved in database");

        return true;
    }

    /**
     * Save book in database
     *
     * @param book to save in database
     * @return if saved return true
     */

    public boolean persistBook(Book book) {
        session.persist(book);
        logger.info("Book saved in database");

        return true;
    }

    /**
     * Delete bookStore from database
     *
     * @param bookStore to delete in database
     */

    public void deleteBookStore(BookStore bookStore) {
        session.delete(bookStore);
    }

    /**
     * Method to obtain session
     *
     * @return session from sessionFactory
     */

    public Session getSession(){
        return session;
    }

}

