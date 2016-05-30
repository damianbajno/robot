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
 * Class for declaring necessary dao component
 * SessionFactory and required methods
 * @author Stycz
 * @version 1.0
 */
public class Dao {
    private Logger logger = Logger.getLogger(Dao.class);
    private Session session;

    private static final Configuration configuration = new Configuration()
            .configure().addAnnotatedClass(Book.class)
            .addAnnotatedClass(BookStore.class)
            .addAnnotatedClass(Profile.class);
    private static final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties());
    private static final SessionFactory sessionfactory = configuration
            .buildSessionFactory(builder.build());


    protected Dao() {
    }

    protected static Session getSession() {
        return sessionfactory.getCurrentSession();
    }

    protected static void beginTransaction() {
        getSession().beginTransaction();
    }

    protected static void commitTransaction() {
        getSession().getTransaction().commit();
    }

    protected static void rollback() {
        try {
            System.out.println("Transaction started rolling back");
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            System.out.println("Transaction can't roll back");
            e.printStackTrace();
        }

        try {
            System.out.println("Session started closing");
            getSession().close();
        } catch (HibernateException e) {
            System.out.println("Session can't close");
            e.printStackTrace();
        }
    }

    /**
     * Method to close SessionFactory
     */

    public static void closeSessionFactory(){
        if (!sessionfactory.isClosed())
        sessionfactory.close();
    }


}