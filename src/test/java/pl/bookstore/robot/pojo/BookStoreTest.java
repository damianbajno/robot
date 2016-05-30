package pl.bookstore.robot.pojo;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

/**
 * Created by stycz0007 on 11.05.16.
 */
public class BookStoreTest {
    @Test
    public void testIfTwoTheSameBookStoresAreTheSame(){
        BookStore bookStore = new BookStore();
        BookStore bookStore1 = new BookStore();
        boolean result = bookStore.equals(bookStore1);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void testIfParticularParameterOfBookstoreAreTheSameOrNot(){
        SoftAssertions softAssertions = new SoftAssertions();
        BookStore bookStore = new BookStore("nexto.pl", "www.nexto.pl", "Hobbit", "Fantasy");
        BookStore bookStore1 = new BookStore("biblio.pl", "www.biblio.pl", "Hobbit","Fantasy");
        softAssertions.assertThat(bookStore.getName()).isNotEqualTo(bookStore1.getName());
        softAssertions.assertThat(bookStore.getUrl()).isNotEqualTo(bookStore1.getUrl());
        softAssertions.assertThat(bookStore.getSearchForCategory()).isEqualTo(bookStore1.getSearchForCategory());
        softAssertions.assertThat(bookStore.getSearchForTitle()).isEqualTo(bookStore1.getSearchForTitle());
        softAssertions.assertAll();
    }

    @Test
    public void testIfNotNullBookStoreHasNotNullHashCode(){
        BookStore bookStore = new BookStore("nexto.pl", "www.nexto.pl", "Hobbit", "Fantasy");
        Assertions.assertThat(bookStore.hashCode()).isNotNull();
    }

    @Test
    public void testIfToStringReturnsStringRepresentation(){
        BookStore bookStore = new BookStore("nexto.pl", "www.nexto.pl","Hobbit", "Fantasy");
        String expected = "BookStore{" +
                "name='" + "nexto.pl" + '\'' +
                ", url='" + "www.nexto.pl" + '\'' +
                ", searchForTitle='" + "Hobbit" + '\'' +
                ", searchForCategory='" + "Fantasy" + '\'' +
                '}';

        Assertions.assertThat(expected).isEqualTo(bookStore.toString());
    }

    @Test
    public void testIfEmptyBookIsHasParametersAfeterSet(){
        SoftAssertions softAssertions = new SoftAssertions();
        int id = 1;
        BookStore bookStore = new BookStore();
        bookStore.setName("nexto.pl");
        bookStore.setUrl("nexto.pl");
        bookStore.setId(id);
//        bookStore.setSearchForBook("search");
        bookStore.setSearchForTitle("Hobbit");
        softAssertions.assertThat(bookStore.getName()).isNotNull();
        softAssertions.assertThat(bookStore.getUrl()).isNotNull();
        softAssertions.assertThat(bookStore.getId()).isNotNull();
//        softAssertions.assertThat(bookStore.getSearchForBook()).isNotNull();
//        softAssertions.assertThat(bookStore.getSearchForBook()).isNotNull();
        softAssertions.assertThat(bookStore.getSearchForTitle()).isNotNull();
        softAssertions.assertAll();
    }
}
