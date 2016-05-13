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
public class ProfilePersister extends HibernateUtil{
    Logger logger = Logger.getLogger(BookPersister.class);

    /**
     * Method saves to database profile connecting it to bookstore
     *
     * @param profile which will be persisted to databases
     * @param bookStore object which will be connected to profile
     */

    public void persistProfile(Profile profile, BookStore bookStore){
        BookStore loadedBookStore = session.load(BookStore.class, bookStore.getId());
        profile.setBookStore(loadedBookStore);
        session.persist(profile);
    }


}
