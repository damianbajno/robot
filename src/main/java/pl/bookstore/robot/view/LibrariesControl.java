package pl.bookstore.robot.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pl.bookstore.robot.GuiApp;

public class LibrariesControl implements Initializable {
	@FXML
	private ListView<String> allLibraries;
	@FXML
	private ListView<String> activeLibraries;
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
		activeLibraries.setItems(mainApp.getActiveLibrariesList());
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
