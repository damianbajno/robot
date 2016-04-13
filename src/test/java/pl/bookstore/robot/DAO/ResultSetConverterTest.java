package pl.bookstore.robot.DAO;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.BeforeMethod;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by damian on 08.04.16.
 */
public class ResultSetConverterTest {

    Answer<Boolean> answer;

    @BeforeMethod
    public void setMock() {
        answer = new Answer<Boolean>() {
            int i = 0;

            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                i++;
                if (i == 1) return true;
                return false;
            }
        };
    }

    @Test
    public void ifPutResoultSetWithBookItReturnBook() {
        //given
        ResultSet resultSet = mock(ResultSet.class);
        setMock();
        try {

            when(resultSet.next()).thenAnswer(answer);

            when(resultSet.getString(1)).thenReturn("BookTitle");
            when(resultSet.getString(2)).thenReturn("BookCategory");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Book bookExpected = new Book("BookTitle", "BookCategory");

        //when
        List<Book> booksResoult = ResultSetConverter.convertToBook(resultSet);

        //then
        Assertions.assertThat(booksResoult.get(0)).isEqualTo(bookExpected);
    }

    @Test
    public void ifPutResoultSetWithBookStoreItReturnBookStore() {
        //given
        ResultSet resultSet = mock(ResultSet.class);
        String url = "URL";

        setMock();
        try {

            when(resultSet.next()).thenAnswer(answer);

            when(resultSet.getString(1)).thenReturn(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        BookStore bookExpected = new BookStore(url);

        //when
        List<BookStore> booksResoult = ResultSetConverter.convertToBookStore(resultSet);

        //then
        Assertions.assertThat(booksResoult.get(0)).isEqualTo(bookExpected);
    }


}
