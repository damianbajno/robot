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
import pl.bookstore.robot.dao.BookStoreDao;
import pl.bookstore.robot.pojo.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LibrariesControl implements Initializable {
    private Logger logger = Logger.getLogger(LibrariesControl.class);
    private ObservableList<BookStore> bookStoreListObservable = FXCollections.observableArrayList();
    private ObservableList<Profile> profileListObservable = FXCollections.observableArrayList();
    private ObservableList<String> categoryListObservable = FXCollections.observableArrayList();
    private BookStoreDao bookStoreDao = new BookStoreDao();
    private List<Book> bookList = new ArrayList<Book>();

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
        bookStoreListObservable.addAll(bookStoreDao.getBookStoreList());

        categoryComboBox.getItems().setAll(categoryListObservable);
        bookStoresListView.setItems(bookStoreListObservable);
        profileChoiceBox.setItems(profileListObservable);

        categoryComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                ObservableList<String> selectedCategory = categoryComboBox.getCheckModel().getCheckedItems();
                filterBooksByCategoryAndAddToTextArea(selectedCategory);
                profileChoiceBox.getSelectionModel().clearSelection();
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
                BookStore selectedBookStore = bookStoresListView.getSelectionModel().getSelectedItem();

                if (selectedBookStore != null) {

                    if (event.getClickCount() == 1) {
                        System.out.println("BookStore ID " + selectedBookStore.getId());
                        fillFieldsByBookStore(selectedBookStore);
                    }

                    if (event.getClickCount() == 2) {
                        booksTextArea.clear();
                        bookList = selectedBookStore.getBookList();
                        bookList.forEach(book -> booksTextArea.appendText(book + "\n"));

                        categoryComboBox.getItems().clear();
                        List<String> categoryList = bookList.stream().map(book -> book.getCategory()).distinct().collect(Collectors.toList());
                        categoryComboBox.getItems().addAll(categoryList);

                        profileListObservable.clear();
                        List<Profile> profileList = selectedBookStore.getProfileList();
                        profileListObservable.addAll(profileList);

                    }
                } else
                    popUpWindowAlertOnSelectionLibrary("Warning category selection",
                            "Warning category selection", "Any category was selected. Please select category.");
            }
        });

        profileChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Profile>() {
            @Override
            public void changed(ObservableValue<? extends Profile> observable, Profile oldValue, Profile newValue) {
                if (newValue != null)
                    filterBooksByCategoryAndAddToTextArea(newValue.getCategories());
            }
        });
    }

    private void filterBooksByCategoryAndAddToTextArea(List<String> categories) {
        booksTextArea.clear();
        bookList.stream().filter(book -> categories.contains(book.getCategory()))
                .forEach(book -> booksTextArea.appendText(book + "\n"));
    }

    @FXML
    private void handleAddModifyButton() {
        BookStore bookStore = fillBookStoreWithFields();
        if (!containBookStoreListLibraryName(bookStoreListObservable, bookStore)) {

            bookStoreListObservable.add(bookStore);
            bookStoreDao.persist(bookStore);
            logger.trace("Added to database " + bookStore.toString());

        } else {
            BookStore selectedBookStore = bookStoresListView.getSelectionModel().getSelectedItem();
            changeFieldsInBookStore(selectedBookStore);
            bookStoreDao.update(selectedBookStore);
        }

    }

    private boolean containBookStoreListLibraryName(Collection<BookStore> bookStores, BookStore bookStore) {
        Predicate<BookStore> bookStoreFilter = bookStoreS -> bookStoreS.getName().equals(bookStore.getName());
        return bookStores.stream().filter(bookStoreFilter).findFirst().isPresent();
    }

    @FXML
    private void removeBookStoreButton() {
        BookStore bookStore = bookStoresListView.getSelectionModel().getSelectedItem();
        if (bookStore != null) {
            bookStoreDao.delete(bookStore);
            bookStoresListView.getItems().remove(bookStore);
        } else {
            popUpWindowAlertOnSelectionLibrary("No Selection", "No Library Selected", "Please select a library.");
        }
    }

    @FXML
    private void addProfileButton() {
        ObservableList<String> selectedCategoryList = categoryComboBox.getCheckModel().getCheckedItems();
        BookStore selectedBookStore = bookStoresListView.getSelectionModel().getSelectedItem();

        try {
            Profile profile = ProfileBuilder.build(selectedCategoryList);
            selectedBookStore.addProfile(profile);
            bookStoreDao.update(selectedBookStore);

            profileListObservable.add(profile);
        } catch (NoCategorySelectedException e) {
            popUpWindowAlertOnSelectionLibrary("Warning category selection",
                    "Warning category selection", "Any category was selected. Please select category.");
        }
    }

    @FXML
    private void handleClearButton(Event event) {
        libraryName.setText("");
        libraryURL.setText("");
        searchForTitle.setText("");
        searchForCategory.setText("");
    }

    private void popUpWindowAlertOnSelectionLibrary(String title, String header, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(null);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
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
        changeFieldsInBookStore(bookStore);
        return bookStore;
    }

    private void changeFieldsInBookStore(BookStore bookStore) {
        bookStore.setName(libraryName.getText());
        bookStore.setUrl(libraryURL.getText());
        bookStore.setSearchForTitle(searchForTitle.getText());
        bookStore.setSearchForCategory(searchForCategory.getText());
    }

}
