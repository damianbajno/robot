package pl.bookstore.robot.Hibernate;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // For XML mapping
            // return new Configuration().configure().buildSessionFactory();

            // For Annotation - wczesniej bylo to AnnotationConfiguration, ale
            // od Hibernate 3.6 jest deprecated i funkcjonalnosci przeniesione
            // do Configuration
            return new Configuration().configure().buildSessionFactory();

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