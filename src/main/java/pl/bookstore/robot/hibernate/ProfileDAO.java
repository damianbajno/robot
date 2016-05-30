package pl.bookstore.robot.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import pl.bookstore.robot.pojo.BookStore;
import pl.bookstore.robot.pojo.Profile;

/**
 * Class which task corresponds to opening,
 * committing, closing session for saving data associated
 * with book profile of interest in database
 * @author Damian
 * @version 1.0
 */
public class ProfileDAO extends DAO {
    private Logger logger = Logger.getLogger(ProfileDAO.class);

    /**
     * Method saves to database profile connecting it to bookstore
     *
     * @param profile which will be persisted to databases
     * @param bookStore object which will be connected to profile
     */

    public void persist(Profile profile, BookStore bookStore){
        try {
            beginTransaction();

            profile.setBookStore(bookStore);
            getSession().persist(profile);
            getSession().update(bookStore);

            commitTransaction();
        } catch (HibernateException e){
            logger.warn("ProfileDao couldn't persist profile");
            e.printStackTrace();
        }
    }



}
