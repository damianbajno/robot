package pl.bookstore.robot.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;

/**
 * Class for declaring necessary hibernate component
 * SessionFactory and required methods
 * @author Stycz
 * @version 1.0
 */

public class HibernateUtil {
    Logger logger = Logger.getLogger(HibernateUtil.class);
    Session session;

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {

            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Method to obtain session
     *
     * @return session from sessionFactory
     */

    public Session getSession(){
        return session;
    }

    /**
     * openSession method to open hibernate sessions
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
}