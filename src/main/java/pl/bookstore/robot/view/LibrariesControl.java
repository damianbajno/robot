package pl.bookstore.robot.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import pl.bookstore.robot.booksearch.Profile;
import pl.bookstore.robot.booksearch.ProfileBuilder;
import pl.bookstore.robot.hibernate.BookPersister;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class LibrariesControl implements Initializable {
    private Logger logger = Logger.getLogger(LibrariesControl.class);
    final String TEXT_IN_INFO_LABEL = "Name of library have to be unique";

    @FXML
    private ChoiceBox<Profile> profileChoiceBox;
    @FXML
    private ListView<BookStore> bookStoresListView;
    @FXML
    private TextArea booksTextArea;
    @FXML
    private TextField libraryName;
    @FXML
    private TextField libraryURL;
    @FXML
    private TextField searchForTitle;
    @FXML
    private TextField searchForCategory;
    @FXML
    private Label infoLabel;


    private ObservableList<BookStore> bookStoreListObservable = FXCollections.observableArrayList();
    private ObservableList<Profile> profileListObservable = FXCollections.observableArrayList();
    private BookPersister bookPersister;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookPersister = new BookPersister();
        bookPersister.openSession();
        List<BookStore> bookStores = bookPersister.getBookStores();
        bookPersister.commitSession();

        bookStoreListObservable.addAll(bookStores);


        bookStoresListView.setCellFactory(new Callback<ListView<BookStore>, ListCell<BookStore>>() {
            @Override
            public ListCell<BookStore> call(ListView<BookStore> param) {
                return new ListCell<BookStore>() {
                    @Override
                    protected void updateItem(BookStore item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) setText(item.getName());
                        else setText("");
                    }
                };
            }
        });

        bookStoresListView.setItems(bookStoreListObservable);
        bookStoresListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    BookStore bookStore = bookStoresListView.getSelectionModel().getSelectedItem();
                    fillFieldsByBookStore(bookStore);
                }

                if (event.getClickCount() == 2) {
                    bookPersister.openSession();
                    ObservableList<BookStore> selectedBookStore = bookStoresListView.getSelectionModel().getSelectedItems();
                    int FIRST_ITEM_ONLY_ITEM_IN_LIST = 0;
                    List<Book> books = bookPersister.getBookFromBookStore(selectedBookStore.get(FIRST_ITEM_ONLY_ITEM_IN_LIST));
                    bookPersister.commitSession();
                    booksTextArea.clear();
                    books.forEach(book -> booksTextArea.appendText(book+"\n"));
                }
            }
        });

        ProfileBuilder profileBuilder=new ProfileBuilder();
        profileListObservable.add(profileBuilder.getProfile1());
        profileChoiceBox.setItems(profileListObservable);
    }

    @FXML
    public void chooseProfile(){

    }

    @FXML
    public void handleAddModifyButton() {
        BookStore bookStore = fillBookStoreWithFields();
        if (!containBookStoreListLibraryName(bookStoreListObservable, bookStore)) {
            bookStoreListObservable.add(bookStore);
            bookPersister.openSession();
            bookPersister.saveBookStore(bookStore);
            bookPersister.commitSession();
            logger.info("Added to database " + bookStore.toString());
        } else {
            BookStore selectedBookStore = bookStoresListView.getSelectionModel().getSelectedItem();
            bookPersister.openSession();
            changeFieldsInBookStore(selectedBookStore);
            bookPersister.updateBookStore(selectedBookStore);
            bookPersister.commitSession();
        }

    }

    public boolean containBookStoreListLibraryName(Collection<BookStore> bookStores, BookStore bookStore) {
        Predicate<BookStore> bookStoreFilter = bookStoreS -> bookStoreS.getName().equals(bookStore.getName());
        return bookStores.stream().filter(bookStoreFilter).findFirst().isPresent();
    }

    @FXML
    private void handleRemoveButton() {
        int selectedIndex = bookStoresListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            BookStore bookStore = bookStoresListView.getItems().get(selectedIndex);
            bookPersister.openSession();
            bookPersister.deleteBookStore(bookStore);
            bookPersister.commitSession();
            bookStoresListView.getItems().remove(selectedIndex);
        } else {
            popUpWindowAlertOnSelectionLibrary();
        }
    }

    @FXML
    public void handleClearButton(Event event) {
        libraryName.setText("");
        libraryURL.setText("");
        searchForTitle.setText("");
        searchForCategory.setText("");
    }

    private void popUpWindowAlertOnSelectionLibrary() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(null);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Library Selected");
        alert.setContentText("Please select a library.");
        alert.showAndWait();
    }

    private void fillFieldsByBookStore(BookStore bookStore) {
        libraryName.setText(bookStore.getName());
        libraryURL.setText(bookStore.getUrl());
        searchForTitle.setText(bookStore.getSearchForTitle());
        searchForCategory.setText(bookStore.getSearchForCategory());
    }

    private BookStore fillBookStoreWithFields() {
        BookStore bookStore = new BookStore();
        bookStore.setName(libraryName.getText());
        bookStore.setUrl(libraryURL.getText());
        bookStore.setSearchForTitle(searchForTitle.getText());
        bookStore.setSearchForCategory(searchForCategory.getText());
        return bookStore;
    }

    private void changeFieldsInBookStore(BookStore bookStore) {
        bookStore.setName(libraryName.getText());
        bookStore.setUrl(libraryURL.getText());
        bookStore.setSearchForTitle(searchForTitle.getText());
        bookStore.setSearchForCategory(searchForCategory.getText());
    }
}
