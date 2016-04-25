package pl.bookstore.robot.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import pl.bookstore.robot.DAO.BookDAO;
import pl.bookstore.robot.DAO.BookStoreDAO;
import pl.bookstore.robot.booksearch.Profile;
import pl.bookstore.robot.booksearch.ProfileBuilder;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class LibrariesControl implements Initializable {
    private Logger logger = Logger.getLogger(LibrariesControl.class);

    @FXML
    private ChoiceBox<Profile> profileChoiceBox;
    @FXML
    private ListView<BookStore> librariesListView;
    @FXML
    private TextArea booksTextArea;
    @FXML
    private TextField libraryName;
    @FXML
    private TextField libraryURL;
    @FXML
    private TextField searchForBook;
    @FXML
    private TextField searchForTitle;
    @FXML
    private TextField searchForCategory;

    private ObservableList<BookStore> bookStoreListObservable = FXCollections.observableArrayList();
    private ObservableList<Profile> profileListObservable = FXCollections.observableArrayList();
    private BookStoreDAO bookStoreDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookStoreDAO = new BookStoreDAO();
        List<BookStore> bookStores = bookStoreDAO.getBookStores();
        bookStoreListObservable.addAll(bookStores);

        librariesListView.setCellFactory(new Callback<ListView<BookStore>, ListCell<BookStore>>() {
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

        librariesListView.setItems(bookStoreListObservable);
        librariesListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    BookStore bookStore = librariesListView.getSelectionModel().getSelectedItem();
                    fillFieldsByBookStore(bookStore);
                }

                if (event.getClickCount() == 2) {
                    BookDAO bookDAO=new BookDAO();
                    List<Book> books = bookDAO.getBooks();
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
    private void handleAddModifyButton() {
        BookStore bookStore = fillBookStoreWithFields();
        if (containBookStoreListLibraryName(bookStoreListObservable, bookStore.getName())) {
            bookStoreListObservable.add(bookStore);
            bookStoreDAO.persist(bookStore);
            logger.info("Added to database " + bookStore.toString());
        }

    }

    @FXML
    private void handleRemoveButton() {
        int selectedIndex = librariesListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            BookStore bookStore = librariesListView.getItems().get(selectedIndex);
            bookStoreDAO.remove(bookStore); // TODO after implement hibernate
            librariesListView.getItems().remove(selectedIndex);
        } else {
            popUpWindowAlertOnSelectionLibrary();
        }
    }

    public boolean containBookStoreListLibraryName(Collection<BookStore> bookStores, String name) {
        return bookStores.stream().filter(bookStore -> bookStore.getName().equals(name)).findFirst().isPresent();
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
        searchForBook.setText(bookStore.getSearchForBook());
        searchForTitle.setText(bookStore.getSearchForTitle());
        searchForCategory.setText(bookStore.getSearchForCategory());
    }

    private BookStore fillBookStoreWithFields() {
        BookStore bookStore = new BookStore();
        bookStore.setName(libraryName.getText());
        bookStore.setUrl(libraryURL.getText());
        bookStore.setSearchForBook(searchForBook.getText());
        bookStore.setSearchForTitle(searchForTitle.getText());
        bookStore.setSearchForCategory(searchForCategory.getText());
        return bookStore;
    }
}
