package pl.bookstore.robot.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;
import pl.bookstore.robot.pojo.Profile;

/**
 * Create sessionFactory, begin, commit, rollback and close session.
 *
 * @author Stycz
 * @version 1.0
 */
public class Dao {
    private static Logger logger = Logger.getLogger(Dao.class);

    private static final Configuration configuration = new Configuration()
            .configure().addAnnotatedClass(Book.class)
            .addAnnotatedClass(BookStore.class)
            .addAnnotatedClass(Profile.class);
    private static final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties());
    private static final SessionFactory sessionfactory = configuration
            .buildSessionFactory(builder.build());


    Dao() {
    }

    static Session getSession() {
        return sessionfactory.getCurrentSession();
    }

    static void beginTransaction() {
        getSession().beginTransaction();
    }

    static void commitTransaction() {
        getSession().getTransaction().commit();
    }

    static void rollback() {
        try {
            logger.warn("Transaction started rolling back");
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            logger.warn("Transaction can't roll back");
            e.printStackTrace();
        }

        try {
            logger.warn("Session started closing");
            getSession().close();
        } catch (HibernateException e) {
            logger.warn("Session can't close");
            e.printStackTrace();
        }
    }

    /**
     * Method to close SessionFactory
     */

    public static void closeSessionFactory(){
        if (!sessionfactory.isClosed()) {
            logger.trace("Closed SessionFactory");
            sessionfactory.close();
        }
    }


}