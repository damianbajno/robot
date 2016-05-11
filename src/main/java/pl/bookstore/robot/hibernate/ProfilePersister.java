package pl.bookstore.robot.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import pl.bookstore.robot.pojo.BookStore;
import pl.bookstore.robot.pojo.Profile;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class which task corresponds to opening,
 * committing, closing session for saving data associated
 * with book profile of interest in database
 * @author Damian
 * @version 1.0
 */
public class ProfilePersister {
    Logger logger = Logger.getLogger(BookPersister.class);
    SessionFactory sessionFactory = pl.bookstore.robot.hibernate.HibernateUtil.getSessionFactory();
    Session session;


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

    /**
     *
     * Method saves to database profile connecting it to bookstore
     *
     * @param profile which will be persisted to databases
     * @param bookStore object which will be connected to profile
     */

    public void persistProfile(Profile profile, BookStore bookStore){
        openSession();
        BookStore bookStoreLoaded= session.load(BookStore.class, bookStore.getId());
        profile.setBookStore(bookStoreLoaded);
        session.persist(profile);
        commitSession();
    }

    /**

     * getProfilesFromBookStore method to extract book profile from database
     * @see Profile  and
     * @see ProcessBuilder classes
     * @return list of book profiles

     *
     * Methods retrieves all profiles connected to bookstore from database
     *
      * @param bookStore object saved to database
     * @return list of profiles

     */

    public List<Profile> getProfilesFromBookStore(BookStore bookStore){
        openSession();
        Criteria criteria = session.createCriteria(Profile.class);
        List<Profile> profileList = criteria.add(Restrictions.eq("bookStore", bookStore)).list();
        commitSession();
        return profileList.stream().distinct().collect(Collectors.toList());
    };
}
