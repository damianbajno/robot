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
     * commitSesion method to commit changes to database
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
     * saveBookStore method to save particular bookstore in database
     * @return true if bookstore is saved successfully
     * @param bookStore is a instance of BookStore class
     */

    public boolean saveBookStore(BookStore bookStore) {
        session.save(bookStore);
        logger.info("Book store saved in database");

        return true;
    }

    /**
     * updateBookStore method to update particular bookstore in database
     * @return true if database os updated successfully
     * @param bookStore is a instance of BookStore class
     */
    public boolean updateBookStore(BookStore bookStore){
        session.update(bookStore);
        logger.info("Book store updated");

        return true;
    }

    /**
     * saveBookStores method to save bookstores in database
     * @return true if bookstores are added correctly
     * @param bookStoreList is a list of instances of BookStore class
     */
    public boolean saveBookStores(List<BookStore> bookStoreList){
        bookStoreList.forEach(booksStore -> session.save(booksStore) );
        logger.info("Book stores saved in database");

        return true;
    }

    /**
     * getBookStore method to extract particular bookstore from database
     * @return first element (zero element) of bookStores list
     * @param name is the book store name which we want to retrieve
     */
    public BookStore getBookStore(String name){
        Criteria criteria = session.createCriteria(BookStore.class);
        List<BookStore> bookStores = criteria.add(Restrictions.eq("name", name)).list();
        return bookStores.get(0);
    }


    /**
     * @param number is the number of books which we want to retrieve
     * @return particular bookstore
     */
    public BookStore getBookStore(int number){
        return session.get(BookStore.class, number);
    }

    /**
     * getBookStores method to extract whole bookstores in database
     * @return list of bookstores
     */
    public List<BookStore> getBookStores(){
        Query getBookStoreQuery = session.createQuery("from "+BookStore.class.getSimpleName());
        return  getBookStoreQuery.list();
    }

    /**
     * getBook method to extract book from database
     * @param title is the book title
     * @return list of bookstores
     */
    public Book getBook(String title){
        Query getBookResult = session.createQuery("FROM "+Book.class.getSimpleName()+" B where B.title='"+title+"'");
        Book bookFromDB = (Book) getBookResult;
        return bookFromDB;
    }
    /**
     * saveBook method to save book list in database
     * @param bookList is the book title
     * @return true if book list is saved correctly
     */
    public boolean saveBooks(List<Book> bookList) {
        bookList.forEach(book -> session.save(book));
        logger.info("Book saved in database");

        return true;
    }
    /**
     * saveBook method to save book in database
     * @param book is the book title
     * @return true if book is saved correctly
     */

    public boolean saveBook(Book book) {
        session.save(book);
        logger.info("Book saved in database");

        return true;
    }
    /**
     * deleteBookStore method to remove book store from database
     * @param bookStore is the book store name
     */
    public void deleteBookStore(BookStore bookStore) {
        session.delete(bookStore);
    }

    /**
     * deleteBook method to remove book from database
     * @param book is the book name
     *
     */
    public void deleteBook(Book book) {
        session.delete(book);
    }

    /**
     * getBooks method to retrieve books from database
     * @return list of books
     */
    public List<Book> getBooks() {
        Query getBookQuery = session.createQuery("from "+Book.class.getSimpleName());
        return getBookQuery.list();
    }
    /**
     * getBookFromBookStore method to retrieve books from database
     * @param bookStore is the BookStore object
     * @see BookStore
     * @return list of books from particular book store
     */
    public List<Book> getBookFromBookStore(BookStore bookStore) {
        Criteria criteria = session.createCriteria(Book.class);
        criteria.add(Restrictions.eq("bookStore", bookStore));
        return criteria.list();
    }

    @Getter
    public Session getSession(){
        return session;
    }

}

