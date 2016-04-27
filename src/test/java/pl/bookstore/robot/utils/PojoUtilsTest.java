package pl.bookstore.robot.utils;

import org.testng.annotations.Test;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by damian on 30.03.16.
 */
public class PojoUtilsTest {

    @Test
    public void testIfMethodReturnFieldsRepresentationOfBookClass(){
        //given

        //when
        String fieldsWithTypes = PojoUtils.getFieldsWithTypes(Book.class);

        //then
        assertThat(fieldsWithTypes).isEqualTo("id int not null unique, title String, category String, bookStore BookStore");
    }

    @Test
    public void testIfMethodReturnFieldsRepresentationOfBookStoreClass(){
        //given

        //when
        String fieldsWithTypes = PojoUtils.getFieldsWithTypes(BookStore.class);

        //then
        assertThat(fieldsWithTypes).isEqualTo("id int not null unique, name String, url String, searchForBook String, searchForTitle String, searchForCategory String, bookList List");
    }
}
