package pl.bookstore.robot.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import pl.bookstore.robot.pojo.BookStore;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stycz0007 on 13.04.16.
 */


public class BookStorePersister {
    public static void main(String[]args){
        BookStorePersister bookPersister = new BookStorePersister();
        List<BookStore> bList = new ArrayList<>();
        bList.add(new BookStore());
        bookPersister.saveBooks(bList);
    }
    boolean saveBooks(List<BookStore> bookList){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (BookStore booksStore : bookList) {

            session.save(booksStore);

        }
        session.getTransaction().commit();
        sessionFactory.close();

        return true;
    }
}
