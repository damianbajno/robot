package pl.bookstore.robot.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import pl.bookstore.robot.pojo.BookStore;
import pl.bookstore.robot.pojo.Profile;

import java.util.List;

/**
 * Created by damian on 5/2/16.
 */
public class ProfilePersister {
    Logger logger = Logger.getLogger(BookPersister.class);
    SessionFactory sessionFactory = pl.bookstore.robot.hibernate.HibernateUtil.getSessionFactory();
    Session session;

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


    public void persistProfile(Profile profile, BookStore bookStore){
        openSession();
        BookStore bookStoreLoaded= session.load(BookStore.class, bookStore.getId());
        profile.setBookStore(bookStoreLoaded);
        session.persist(profile);
        commitSession();
    }

    public List<Profile> getProfilesFromBookStore(BookStore bookStore){
        openSession();
        Criteria criteria = session.createCriteria(Profile.class);
        List<Profile> profileList = criteria.add(Restrictions.eq("bookStore", bookStore)).list();
        commitSession();
        return profileList;
    };
}
