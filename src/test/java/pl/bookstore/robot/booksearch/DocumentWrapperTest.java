package pl.bookstore.robot.booksearch;

import com.jaunt.Document;
import com.jaunt.ResponseException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.bookstore.robot.booksearch.DocumentWrapper;
import pl.bookstore.robot.helper.BookStoreContainer;
import pl.bookstore.robot.pojo.BookStore;

/**
 * Created by damian on 6/2/16.
 */
public class DocumentWrapperTest {

    @DataProvider(name = "bookStoreProvider")
    public Object[][] dataProviderForSearchABookInDocument() {
        BookStore[] bookStores = BookStoreContainer.getBookStores();

        Object[][] data = {
                {bookStores[0]},
                {bookStores[1]},
                {bookStores[2]}
        };
        return data;
    }

    @Test(dataProvider = "bookStoreProvider", groups = "IntegrationTest")
    public void getDocumentFromLinkIfLinkIsValid(BookStore bookStore) throws ResponseException {
        //when
        Document receivedDocument = DocumentWrapper.getDocumentFrom(bookStore.getUrl());

        //then
        Assertions.assertThat(receivedDocument.getRoot()).isNotNull();
    }

    @Test
    public void throwExceptionWhenTryToConnectToNotValidUrl() {
        //given
        String notValidUrl = "fdasasdfdsa";

        //then
        Assertions.assertThatExceptionOfType(ResponseException.class).isThrownBy(() ->
                DocumentWrapper.getDocumentFrom(notValidUrl));
    }


}
