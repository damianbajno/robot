package pl.bookstore.robot.hibernate;
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
 * BookPersister class created for saving data to database
 * using hibernate.
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

    public  boolean openSession(){
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
        session.close();
      return true;
    }

    public boolean closeSessionFactory(){
        sessionFactory.close();
        logger.warn("Session closed");

        return true;
    }

    public boolean saveBookStore(BookStore bookStore){
        session.save(bookStore);
        logger.info("Book store saved in database");

        return true;
    }

    public boolean updateBookStore(BookStore bookStore){
        session.update(bookStore);
        logger.info("Book store saved in database");

        return true;
    }

    public boolean saveBookStores(List<BookStore> bookStoreList){
        bookStoreList.forEach(booksStore -> session.save(booksStore) );
        logger.info("Book stores saved in database");

        return true;
    }

    public BookStore getBookStore(String name){
        Criteria criteria = session.createCriteria(BookStore.class);
        List<BookStore> bookStores = criteria.add(Restrictions.eq("name", name)).list();
        return bookStores.get(0);
    }

    public BookStore getBookStore(int number){
        return session.get(BookStore.class, number);
    }

    public List<BookStore> getBookStores(){
        Query getBookStoreQuery = session.createQuery("from "+BookStore.class.getSimpleName());
        return  getBookStoreQuery.list();
    }

    public Book getBook(String title){
        Query getBookResult = session.createQuery("FROM "+Book.class.getSimpleName()+" B where B.title='"+title+"'");
        Book bookFromDB = (Book) getBookResult;
        return bookFromDB;
    }

    public  boolean saveBooks(List<Book> bookList) {
        bookList.forEach(book -> session.save(book));
        logger.info("Book saved in database");

        return true;
    }

    public boolean saveBook(Book book) {
        session.save(book);
        logger.info("Book saved in database");

        return true;
    }

    public void deleteBookStore(BookStore bookStore) {
        session.delete(bookStore);
    }

    public void deleteBook(Book book) {
        session.delete(book);
    }

    public List<Book> getBooks() {
        Query getBookQuery = session.createQuery("from "+Book.class.getSimpleName());
        return getBookQuery.list();
    }

    public List<Book> getBookFromBookStore(BookStore bookStore) {
        Criteria criteria = session.createCriteria(Book.class);
        criteria.add(Restrictions.eq("bookStore", bookStore));
        return criteria.list();
    }

    public Session getSession(){
        return session;
    }

}

