package pl.bookstore.robot.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import pl.bookstore.robot.pojo.BookStore;

import java.util.List;

/**
 * Saving, updating, getting, deleting bookstores from databases.
 *
 * Created by damian on 5/30/16.
 */
public class BookStoreDao extends Dao {
    private Logger logger = Logger.getLogger(BookStoreDao.class);

    /**
     * persist method to save particular bookstore in database
     *
     * @param bookStore object to save in database
     */
    public void persist(BookStore bookStore) {
        try {
            beginTransaction();
            getSession().persist(bookStore);
            commitTransaction();

            logger.trace("BookStore saved to database = "+bookStore.toString());
        } catch (HibernateException e) {
            logger.error("BookDao couldn't persist " + bookStore.toString());
            rollback();
        }
    }


    /**
     * update method to update particular bookstore in database
     *
     * @param bookStore object will be updated in database
     */
    public void update(BookStore bookStore) {
        try {
            beginTransaction();
            getSession().update(bookStore);
            commitTransaction();
        } catch (HibernateException e) {
            logger.error("BookStoreDao couldn't update " + bookStore.toString());
            e.printStackTrace();
            rollback();
        }
    }


    /**
     * Retrieve all BookStores from database
     *
     * @return List of BookStores
     */
    public List<BookStore> getBookStoreList() {
        List<BookStore> bookStoreList = null;
        try {
            beginTransaction();
            Query query = getSession().createQuery("from " + BookStore.class.getSimpleName());
            bookStoreList = query.list();
            commitTransaction();
        } catch (HibernateException e) {
            logger.error("BookStoreDao couldn't get BookStoresList");
            e.printStackTrace();
            rollback();
        }
        return bookStoreList;
    }

    /**
     * Delete bookStore from database
     *
     * @param bookStore to delete in database
     */
    public void delete(BookStore bookStore) {
        try {
            beginTransaction();
            getSession().delete(bookStore);
            commitTransaction();
        } catch (HibernateException e) {
            e.printStackTrace();
            logger.error("BookStoreDao couldn't delete "+ bookStore.toString());
        }
    }
}
