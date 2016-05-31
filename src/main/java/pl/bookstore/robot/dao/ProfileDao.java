package pl.bookstore.robot.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import pl.bookstore.robot.pojo.BookStore;
import pl.bookstore.robot.pojo.Profile;

/**
 * Opens, commits and closes session for book profile.
 * @author Damian
 * @version 1.0
 * @see pl.bookstore.robot.pojo.Profile
 */
public class ProfileDao extends Dao {
    private Logger logger = Logger.getLogger(ProfileDao.class);

    /**
     * Saves bookstore's profile to database.
     *
     * @param profile persisted to databases
     * @param bookStore connected to profile
     */
    public void persist(Profile profile, BookStore bookStore){
        try {
            beginTransaction();

            profile.setBookStore(bookStore);
            getSession().persist(profile);
            getSession().update(bookStore);

            commitTransaction();

            logger.trace("Profile save to database = "+ profile.toString());
        } catch (HibernateException e){
            logger.warn("ProfileDao couldn't persist profile");
            e.printStackTrace();
        }
    }

}
