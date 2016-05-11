package pl.bookstore.robot.pojo;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.Optional;

/**
 * Created by stycz0007 on 11.05.16.
 */
public class BookTest {

    @Test
    public void testAreTwoTheSameBooksAreTheSame(){
        Book book1 = new Book("Hobbit", "Fantasy", new BookStore("nexto.pl"));
        Book book2 = new Book("Hobbit", "Fantasy", new BookStore("nexto.pl"));
        boolean result = book1.equals(book2);
        Assertions.assertThat(result).isTrue();
    }


    @Test
    public void testIfBookIsNotTheSameAsBookStore(){
        Book book1 = new Book("Hobbit", "Fantasy", new BookStore("nexto.pl"));
        BookStore bookStore = new BookStore();
        boolean result = book1.equals(bookStore);
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void testIfTwoBooksAreTheSameCreatedFromAnotherConstructor(){
        Book book1 = new Book("Hobbit", "Fantasy");
        Book book2 = new Book("Hobbit", "Fantasy");
        boolean result = book1.equals(book2);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void testIfToStringReturnsInProperWay(){
        BookStore bookStore = new BookStore("nexto.pl");
        Book book1 = new Book("Hobbit", "Fantasy", bookStore);
        String expected = "Book{" +
                "title='" + book1.getTitle() + '\'' +
                ", category='" + book1.getCategory()+ '\'' +
                ", bookStoreName='" + bookStore.getName() + '\'' +
                '}';

        Assertions.assertThat(expected).isEqualTo(book1.toString());
    }

    @Test
    public void testIfEmptyBookReturnsEmptyToString(){
        Book book = new Book();
        String expected = "Book{" +
                "title='" + null + '\'' +
                ", category='" + null+ '\'' +
                ", bookStoreName='" + "brak" + '\'' +
                '}';
    Assertions.assertThat(expected).isEqualTo(book.toString());
    }

    @Test
    public void testIfHashCodeFromBookObjectIsNotNull(){
        Book book = new Book();
        Assertions.assertThat(book.hashCode()).isNotNull();
    }
    @Test
    public void testIfEmptyBookIsHasParametersAfeterSet(){
        SoftAssertions softAssertions = new SoftAssertions();
        int id = 1;
        Book book = new Book();
        book.setTitle("Hobbit");
        book.setCategory("Fantasy");
        book.setId(id);
        book.setBookStore(new BookStore());
        softAssertions.assertThat(book.getTitle()).isNotNull();
        softAssertions.assertThat(book.getCategory()).isNotNull();
        softAssertions.assertThat(book.getId()).isNotNull();
        softAssertions.assertThat(book.getBookStore()).isNotNull();
        softAssertions.assertAll();
    }



}
