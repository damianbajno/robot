package pl.bookstore.robot.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import pl.bookstore.robot.pojo.BookStore;

import java.net.URL;
import java.util.ResourceBundle;

public class LibrariesControl implements Initializable {
	@FXML
	private ListView<BookStore> allLibraries;
	@FXML
	private ListView<BookStore> activeLibraries;
	@FXML
	private TextArea booksPromotion;
	@FXML
	private TextField libraryName;
	@FXML
	private TextField libraryURL;

	// Reference to the main application.
	private GuiApp mainApp = new GuiApp();

	public LibrariesControl() {

	}

	public void setMain(GuiApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// ObservableList<String> librariesList = mainApp.getLibrariesList();
		allLibraries.setItems(mainApp.getAllLibrariesList());
		final Callback<ListView<BookStore>, ListCell<BookStore>> listCellBookStore = new Callback<ListView<BookStore>, ListCell<BookStore>>() {
			@Override
			public ListCell<BookStore> call(ListView<BookStore> param) {
				return new ListCell<BookStore>() {
					@Override
					protected void updateItem(BookStore item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null) setText(item.getName());
					}
				};
			}
		};

		allLibraries.setCellFactory(listCellBookStore);
		activeLibraries.setItems(mainApp.getActiveLibrariesList());
		activeLibraries.setCellFactory(listCellBookStore);
	}

	@FXML
	private void handleRemoveLibrary() {
		int selectedIndex = allLibraries.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			allLibraries.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Library Selected");
			alert.setContentText("Please select a library.");
			alert.showAndWait();
		}
	}

	@FXML
	private void handleAddLibrary() {
		// String selectedString = libraryName.getSelection().;
	}
}
