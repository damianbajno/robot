package pl.bookstore.robot.pojo;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by stycz0007 on 11.05.16.
 */
public class BookTest {

    @DataProvider(name = "BooksData")
    public Object[][] getBooks() {
        Book book = new Book();
        Object[][] bookArray = {
                {book, book, true},
                {new Book("title"), null, false},

                {new Book(null), new Book("title"), false},
                {new Book("title"), new Book("title"), true},
                {new Book("title"), new Book("title1"), false},

                {new Book("title", null), new Book("title", "category"), false},
                {new Book(null, "category"), new Book("title", "category"), false},
                {new Book("title", "category"), new Book("title", null), false},
                {new Book("title", "category"), new Book(null, "category"), false},

                {new Book("title", "category"), new Book("title", "category"), true},
                {new Book("title", "category"), new Book("title1", "category"), false},
                {new Book("title", "category"), new Book("title", "category1"), false},

                {new Book("title", "category", new BookStore()), new Book("title", "category", new BookStore()), true},
                {new Book("title", "category", new BookStore()), new Book("title", "category", new BookStore("name")), true},
        };
        return bookArray;
    }

    @Test(dataProvider = "BooksData", groups = "NewTest")
    public void testBookStoreEqualMethod(Book firstBook, Book secondBook, boolean equal) {
        //when
        boolean equalResult = firstBook.equals(secondBook);

        //then
        Assertions.assertThat(equalResult).isEqualTo(equal);
    }

    @Test(dataProvider = "BooksData", groups = "NewTest")
    public void testContractBetweenEqualAndHashCode(Book firstBook, Book secondBook, Boolean equal) {
        //when
        if (firstBook != null && secondBook != null) {
            int firstProfileHashCode = firstBook.hashCode();
            int secondProfileHashCode = secondBook.hashCode();
            boolean hashCodeResoult = (firstProfileHashCode == secondProfileHashCode);

            Assertions.assertThat(hashCodeResoult).isEqualTo(equal);
        }
    }

    @Test
    public void testAreTwoTheSameBooksAreTheSame() {
        Book book1 = new Book("Hobbit", "Fantasy", new BookStore("nexto.pl"));
        Book book2 = new Book("Hobbit", "Fantasy", new BookStore("nexto.pl"));
        boolean result = book1.equals(book2);
        Assertions.assertThat(result).isTrue();
    }


    @Test
    public void testIfBookIsNotTheSameAsBookStore() {
        Book book1 = new Book("Hobbit", "Fantasy", new BookStore("nexto.pl"));
        BookStore bookStore = new BookStore();
        boolean result = book1.equals(bookStore);
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void testIfTwoBooksAreTheSameCreatedFromAnotherConstructor() {
        Book book1 = new Book("Hobbit", "Fantasy");
        Book book2 = new Book("Hobbit", "Fantasy");
        boolean result = book1.equals(book2);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void testIfHashCodeFromBookObjectIsNotNull() {
        Book book = new Book();
        Assertions.assertThat(book.hashCode()).isNotNull();
    }

    @Test
    public void testIfEmptyBookHasParametersAfterSet() {
        SoftAssertions softAssertions = new SoftAssertions();
        int id = 1;
        Book book = new Book();
        book.setTitle("Hobbit");
        book.setCategory("Fantasy");
        book.setId(id);

        softAssertions.assertThat(book.getTitle()).isNotNull();
        softAssertions.assertThat(book.getCategory()).isNotNull();
        softAssertions.assertThat(book.getId()).isNotNull();
        softAssertions.assertAll();
    }


}
