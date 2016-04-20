package pl.bookstore.robot.hibernate;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;
import java.util.List;

/**
 * BookPersister class created for saving data to database
 * using hibernate.
 * @author  Stycz
 * @version 1.0
 * @since   2016-04-14
 */

public class BookPersister {
     Logger logger = Logger.getLogger(BookPersister.class);
     SessionFactory sessionFactory;
     Session session;

    public BookPersister(){

    }

    public static void main(String[] args) {
        SessionFactory  sessionFactory = HibernateUtil.getSessionFactory();
        Session  session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Book());
        session.getTransaction().commit();
        sessionFactory.close();

    }
    public  boolean openSession(){
        sessionFactory = HibernateUtil.getSessionFactory();
        logger.info("sessionFactory created");
        session = sessionFactory.openSession();
        session.beginTransaction();
        logger.warn("Session opened");

        return true;
    }
    public  boolean closeSession(){
        session.getTransaction().commit();
        if (session.getTransaction().getStatus() != TransactionStatus.COMMITTED) {
            logger.error("Session status different than COMITTED");
            return false;
        }
        sessionFactory.close();
        logger.warn("Session closed");

        return true;
    }

    public  boolean saveBookStore(List<BookStore> bookStoreList){
        bookStoreList.forEach(booksStore -> session.save(booksStore) );
        logger.info("Book stores saved in database");

        return true;
    }

    public BookStore getBookStore(int key){
        return session.get(BookStore.class, key);
    }

    public Book getBook(int key){
        openSession();
        Book bookFromDB = session.get(Book.class, key);
        closeSession();
        return bookFromDB;
    }

    public  boolean saveBooks(List<Book> bookList) {
        bookList.forEach(books -> session.save(books));
        logger.info("Book saved in database");

        return true;
    }


    }

