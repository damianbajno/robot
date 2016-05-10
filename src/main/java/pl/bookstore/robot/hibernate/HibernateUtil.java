package pl.bookstore.robot.hibernate;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Class for declaring necessary hibernate component
 * SessionFactory and required methods
 * @author Stycz
 * @version 1.0
 */

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {

            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        } catch (Throwable ex) {

            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}