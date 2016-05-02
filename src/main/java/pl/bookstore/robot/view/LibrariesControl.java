package pl.bookstore.robot.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import org.controlsfx.control.CheckComboBox;
import pl.bookstore.robot.hibernate.ProfilePersister;
import pl.bookstore.robot.pojo.*;
import pl.bookstore.robot.hibernate.BookPersister;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LibrariesControl implements Initializable {
    private int FIRST_ITEM_ONLY_ITEM_IN_LIST = 0;

    private Logger logger = Logger.getLogger(LibrariesControl.class);
    private ObservableList<BookStore> bookStoreListObservable = FXCollections.observableArrayList();
    private ObservableList<Profile> profileListObservable = FXCollections.observableArrayList();
    private ObservableList<String> categoryListObservable = FXCollections.observableArrayList();
    private BookPersister bookPersister = new BookPersister();
    private ProfilePersister profilePersister =new ProfilePersister();
    private List<Book> bookShowList;

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
    private CheckComboBox<String> categoryComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<BookStore> bookStores = getBookStores();

        bookStoreListObservable.addAll(bookStores);

        categoryComboBox.getItems().setAll(categoryListObservable);
        bookStoresListView.setItems(bookStoreListObservable);
        profileChoiceBox.setItems(profileListObservable);

        categoryComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                ObservableList<String> selectedCategory = categoryComboBox.getCheckModel().getCheckedItems();
                filterBooksByCategoryAndAddToTextArea(selectedCategory);
            }
        });


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

        bookStoresListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    BookStore bookStore = bookStoresListView.getSelectionModel().getSelectedItem();
                    fillFieldsByBookStore(bookStore);
                }

                if (event.getClickCount() == 2) {
                    ObservableList<BookStore> selectedBookStoreList = bookStoresListView.getSelectionModel().getSelectedItems();
                    BookStore selectedBookStore = selectedBookStoreList.get(FIRST_ITEM_ONLY_ITEM_IN_LIST);

                    bookPersister.openSession();
                    bookShowList = bookPersister.getBookFromBookStore(selectedBookStore);
                    bookPersister.commitSession();

                    booksTextArea.clear();
                    bookShowList.forEach(book -> booksTextArea.appendText(book + "\n"));

                    categoryComboBox.getItems().clear();
                    List<String> categoryList = bookShowList.stream().map(book -> book.getCategory()).distinct().collect(Collectors.toList());
                    categoryComboBox.getItems().addAll(categoryList);

                    profileListObservable.clear();
                    List<Profile> profileList = profilePersister.getProfilesFromBookStore(selectedBookStore);
                    profileListObservable.addAll(profileList);
                }
            }
        });

        profileChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Profile>() {
            @Override
            public void changed(ObservableValue<? extends Profile> observable, Profile oldValue, Profile newValue) {

            }
        });
    }

    private List<BookStore> getBookStores() {
        bookPersister.openSession();
        List<BookStore> bookStores = bookPersister.getBookStores();
        bookPersister.commitSession();
        return bookStores;
    }

    private void filterBooksByCategoryAndAddToTextArea(List<String> categories) {
        booksTextArea.clear();
        bookShowList.stream().filter(book -> {
            return categories.contains(book.getCategory());
        }).forEach(book -> booksTextArea.appendText(book + "\n"));
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
    private void removeBookStoreButton() {
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
    public void addProfileButton() {
        ObservableList<String> selectedCategoryList = categoryComboBox.getCheckModel().getCheckedItems();
        BookStore selectedBookStore = bookStoresListView.getSelectionModel().getSelectedItem();

        Profile profile = ProfileBuilder.build(selectedCategoryList);
        profilePersister.persistProfile(profile, selectedBookStore);

        profileListObservable.add(profile);
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
