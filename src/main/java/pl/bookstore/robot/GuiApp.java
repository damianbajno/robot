package pl.bookstore.robot;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GuiApp extends Application {

	private Stage primaryStage;
	private AnchorPane rootLayout;

	private ObservableList<String> allLibrariesList = FXCollections.observableArrayList();
	private ObservableList<String> activeLibrariesList = FXCollections.observableArrayList();

	public GuiApp() {
		allLibrariesList.add(new String("Ekiosk24"));
		allLibrariesList.add(new String("Gandalf"));
		allLibrariesList.add(new String("Matras"));
		allLibrariesList.add(new String("Pwn"));

		activeLibrariesList.add(new String("Ekiosk24"));
		activeLibrariesList.add(new String("Pwn"));
	}

	public ObservableList<String> getAllLibrariesList() {
		return allLibrariesList;
	}

	public ObservableList<String> getActiveLibrariesList() {
		return activeLibrariesList;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Bookstore");

		showBookstore();
	}

	public void showBookstore() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiApp.class.getResource("view/Libraries.fxml"));
			rootLayout = (AnchorPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void transferRigth() {

	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
