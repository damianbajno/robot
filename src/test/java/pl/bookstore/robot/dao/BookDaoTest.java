package pl.bookstore.robot.dao;

import org.assertj.core.api.Assertions;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.spi.id.local.LocalTemporaryTableBulkIdStrategy;
import org.testng.annotations.Test;
import pl.bookstore.robot.pojo.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stycz0007 on 14.04.16.
 */
public class BookDaoTest {


    @Test(groups = "IntegrationTest")
    public void testIfBookIsSavedInDatabase() {
        //given
        BookDao bookDao = new BookDao();
        Book bookExpected = new Book("KSIONSZKA", "To jest dramat k*rwa");
        String query = "from Book where title=\'KSIONSZKA\'";

        //when
        bookDao.persist(bookExpected);

        //then
        Session session = bookDao.getSession();
        session.beginTransaction();
        Query query1 = session.createQuery(query);
        List<Book> bookList = query1.list();
        Assertions.assertThat(bookList).contains(bookExpected);

        //clear database
        bookList.forEach(book -> session.delete(book));
        session.getTransaction().commit();

    }

    @Test(groups = "IntegrationTest")
    public void testIfBookListIsSavedInDatabase() {
        //given
        BookDao bookDao = new BookDao();
        List<Book> bookList = new ArrayList<Book>();
        Book bookExpected = new Book("KSIONSZKA231", "To jest dramat");
        bookList.add(bookExpected);

        String query = "from Book where title=\'KSIONSZKA231\'";

        //when
        bookList.forEach(book -> bookDao.persist(book));

        //then
        Session session = bookDao.getSession();
        session.beginTransaction();
        Query query1 = session.createQuery(query);
        List<Book> bookListFound = query1.list();
        Assertions.assertThat(bookListFound).contains(bookExpected);

        //clear database
        bookListFound.forEach(book -> session.delete(book));
        session.getTransaction().commit();

    }

}
