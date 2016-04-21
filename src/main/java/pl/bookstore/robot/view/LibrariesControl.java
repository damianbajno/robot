package pl.bookstore.robot.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import pl.bookstore.robot.DAO.BookStoreDAO;
import pl.bookstore.robot.pojo.BookStore;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LibrariesControl implements Initializable {
    @FXML
    private ListView<BookStore> librariesListView;
    @FXML
    private TextArea books;
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

    private ObservableList<BookStore> bookStoreList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        retrieveBookStoresFromDatabases();

        BookStoreDAO bookStoreDAO = new BookStoreDAO();
        List<BookStore> bookStores = bookStoreDAO.getBookStores();
        bookStoreList.addAll(bookStores);


        librariesListView.setCellFactory(new Callback<ListView<BookStore>, ListCell<BookStore>>() {
            @Override
            public ListCell<BookStore> call(ListView<BookStore> param) {
                return new ListCell<BookStore>(){
                    @Override
                    protected void updateItem(BookStore item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item!=null) setText(item.getName()); else setText("");
                    }
                };
            }
        });

        librariesListView.setItems(bookStoreList);

    }

    private void retrieveBookStoresFromDatabases() {

    }

    @FXML
    private void handleRemoveLibrary() {
        int selectedIndex = librariesListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            librariesListView.getItems().remove(selectedIndex);
        } else {
            popUpWindowAlertOnSelectionLibrary();
        }
    }

    private void popUpWindowAlertOnSelectionLibrary() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(null);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Library Selected");
        alert.setContentText("Please select a library.");
        alert.showAndWait();
    }

}
