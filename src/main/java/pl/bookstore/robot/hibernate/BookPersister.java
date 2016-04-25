package pl.bookstore.robot.hibernate;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.ArrayList;
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

        BookPersister bookPersister = new BookPersister();
        bookPersister.openSession();
        List<BookStore> bookList = new ArrayList<>();
        bookList.add(new BookStore());
        bookPersister.saveBookStores(bookList);
        bookPersister.commitSession();
        bookPersister.closeSession();

    }

    public  boolean openSession(){
        sessionFactory = pl.bookstore.robot.hibernate.HibernateUtil.getSessionFactory();
        logger.info("sessionFactory created");
        session = sessionFactory.openSession();
        session.beginTransaction();
        logger.warn("Session opened");

        return true;
    }
    public boolean commitSession(){
        session.getTransaction().commit();
        if (session.getTransaction().getStatus() != TransactionStatus.COMMITTED) {
            logger.error("Session status different than COMMITTED");
            return false;
        }
      return true;
    }

    public boolean closeSession(){
        sessionFactory.close();
        logger.warn("Session closed");

        return true;
    }

    public boolean saveBookStore(BookStore bookStore){
        session.save(bookStore);
        logger.info("Book store saved in database");

        return true;
    }

    public boolean saveBookStores(List<BookStore> bookStoreList){
        bookStoreList.forEach(booksStore -> session.save(booksStore) );
        logger.info("Book stores saved in database");

        return true;
    }

    public BookStore getBookStore(String name){
        Query getBookStoreQuery = session.createQuery("FROM "+BookStore.class.getSimpleName()+" BS where BS.name='"+name+"'");
        List<BookStore> bookStores = getBookStoreQuery.list();
        //BookStore bookStoreResult =(BookStore) getBookStoreQuery;
        return bookStores.get(0);
    }

    public Book getBook(String title){
        Query getBookResult = session.createQuery("FROM "+Book.class.getSimpleName()+" B where B.title='"+title+"'");
        Book bookFromDB = (Book) getBookResult;
        return bookFromDB;
    }

    public  boolean saveBooks(List<Book> bookList) {
        bookList.forEach(books -> session.save(books));
        logger.info("Book saved in database");

        return true;
    }


}

