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
 * BookPersister class created for saving data assacioted with books
 * of interest to database using hibernate.
 * @author  Stycz
 * @version 1.0
 * @since   2016-04-14
 */

public class BookPersister {
     Logger logger = Logger.getLogger(BookPersister.class);
     SessionFactory sessionFactory = pl.bookstore.robot.hibernate.HibernateUtil.getSessionFactory();
     Session session;

    public BookPersister(){

    }
    /**
     * openSession method for opening hibernate sessions
     * @return true if session is opened correctly
     */
    public  boolean openSession(){
        logger.info("sessionFactory created");
        session = sessionFactory.openSession();
        session.beginTransaction();
        logger.warn("Session opened");

        return true;
    }

    /**
     * commitSession method to commit changes to database
     * @return true if session is committed correctly
     */
    public boolean commitSession(){
        session.getTransaction().commit();
        if (session.getTransaction().getStatus() != TransactionStatus.COMMITTED) {
            logger.error("Session status different than COMMITTED");
            return false;
        }
        session.close();
      return true;
    }

    /**
     * closeSession method to close hibernate session
     * @return true if session is closed correctly
     */

    public boolean closeSessionFactory(){
        sessionFactory.close();
        logger.warn("Session closed");

        return true;
    }
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
        return  getBookStoreQuery.list();
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
     * Delete book from database
     *
     * @param book to delete in database
     *
     */
    public void deleteBook(Book book) {
        Query selectBooks = session.createQuery("from Book as b where b.title='" + book.getTitle() + "'");
        List<Book> bookList = selectBooks.list();
        for (Book  b : bookList) {
            session.delete(b);
        }
    }

    /**
     * Retrieve all books from database
     *
     * getBooks method to retrieve books from database
     * @return list of books
     */
    public List<Book> getBooks() {
        Query getBookQuery = session.createQuery("from "+Book.class.getSimpleName());
        return getBookQuery.list();
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

