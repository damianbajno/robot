package pl.bookstore.robot.pojo;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by stycz0007 on 11.05.16.
 */
public class BookStoreTest {


    @DataProvider(name = "BookStoresData")
    public Object[][] getBookStores() {
        BookStore bookStore = new BookStore();
        Object[][] bookStoreArray = {
                {bookStore, bookStore, true},

                {new BookStore("", "", "", ""), null, false},

                {new BookStore("", "", "", ""), new BookStore("", "", "", ""), true},
                {new BookStore("name","url","searchTitle","searchCategory"), new BookStore("name","url","searchTitle","searchCategory"), true},
                {new BookStore("name1","url","searchTitle","searchCategory"), new BookStore("name2","url","searchTitle","searchCategory"), false},
                {new BookStore("name","url1","searchTitle","searchCategory"), new BookStore("name","url2","searchTitle","searchCategory"), false},
                {new BookStore("name","url","searchTitle1","searchCategory"), new BookStore("name","url","searchTitle2","searchCategory"), false},
                {new BookStore("name","url","searchTitle","searchCategory1"), new BookStore("name","url","searchTitle","searchCategory2"), false},

                {new BookStore(null,"url","searchTitle","searchCategory"), new BookStore("name2","url","searchTitle","searchCategory"), false},
                {new BookStore("name",null,"searchTitle","searchCategory"), new BookStore("name","url2","searchTitle","searchCategory"), false},
                {new BookStore("name","url", null,"searchCategory"), new BookStore("name","url","searchTitle2","searchCategory"), false},
                {new BookStore("name","url","searchTitle",null), new BookStore("name","url","searchTitle","searchCategory2"), false},

                {new BookStore("name2","url","searchTitle","searchCategory"), new BookStore(null,"url","searchTitle","searchCategory"), false},
                {new BookStore("name","url2","searchTitle","searchCategory"), new BookStore("name",null,"searchTitle","searchCategory"), false},
                {new BookStore("name","url","searchTitle2","searchCategory"), new BookStore("name","url", null,"searchCategory"), false},
                {new BookStore("name","url","searchTitle","searchCategory2"), new BookStore("name","url","searchTitle",null), false},
        };
        return bookStoreArray;
    }

    @Test(dataProvider = "BookStoresData")
    public void testBookStoreEqualMethod(BookStore firstBookStore, BookStore secondBookStore, boolean equal) {
        //when
        boolean equalResult = firstBookStore.equals(secondBookStore);

        //then
        Assertions.assertThat(equalResult).isEqualTo(equal);
    }

    @Test
    public void testIfParticularParameterOfBookstoreAreTheSameOrNot() {
        SoftAssertions softAssertions = new SoftAssertions();
        BookStore bookStore = new BookStore("nexto.pl", "www.nexto.pl", "Hobbit", "Fantasy");
        BookStore bookStore1 = new BookStore("biblio.pl", "www.biblio.pl", "Hobbit", "Fantasy");
        softAssertions.assertThat(bookStore.getName()).isNotEqualTo(bookStore1.getName());
        softAssertions.assertThat(bookStore.getUrl()).isNotEqualTo(bookStore1.getUrl());
        softAssertions.assertThat(bookStore.getSearchForCategory()).isEqualTo(bookStore1.getSearchForCategory());
        softAssertions.assertThat(bookStore.getSearchForTitle()).isEqualTo(bookStore1.getSearchForTitle());
        softAssertions.assertAll();
    }

    @Test(dataProvider = "BookStoresData")
    public void testContractBetweenEqualAndHashCode(BookStore firstBookStore, BookStore secondBookStore, Boolean equal) {
        //when
        if (firstBookStore != null && secondBookStore != null) {
            int firstProfileHashCode = firstBookStore.hashCode();
            int secondProfileHashCode = secondBookStore.hashCode();
            boolean resoultHashCode = (firstProfileHashCode == secondProfileHashCode);

            Assertions.assertThat(resoultHashCode).isEqualTo(equal);
        }
    }

    @Test
    public void testIfNotNullBookStoreHasNotNullHashCode() {
        BookStore bookStore = new BookStore("nexto.pl", "www.nexto.pl", "Hobbit", "Fantasy");
        Assertions.assertThat(bookStore.hashCode()).isNotNull();
    }

    @Test
    public void testIfEmptyBookIsHasParametersAfterSet() {
        SoftAssertions softAssertions = new SoftAssertions();
        int id = 1;
        BookStore bookStore = new BookStore();
        bookStore.setName("nexto.pl");
        bookStore.setUrl("nexto.pl");
        bookStore.setId(id);
        bookStore.setSearchForTitle("Hobbit");
        softAssertions.assertThat(bookStore.getName()).isNotNull();
        softAssertions.assertThat(bookStore.getUrl()).isNotNull();
        softAssertions.assertThat(bookStore.getId()).isNotNull();
        softAssertions.assertThat(bookStore.getSearchForTitle()).isNotNull();
        softAssertions.assertAll();
    }
}
