package pl.bookstore.robot.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.bookstore.robot.DAO.BookStoreDAO;
import pl.bookstore.robot.pojo.BookStore;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GuiApp extends Application {

	private Stage primaryStage;
	private AnchorPane rootLayout;

	private ObservableList<BookStore> allLibrariesList = FXCollections.observableArrayList();
	private ObservableList<BookStore> activeLibrariesList = FXCollections.observableArrayList();

	public GuiApp() {
		BookStoreDAO bookStoreDAO=new BookStoreDAO();
		List<BookStore> bookStores = bookStoreDAO.getBookStores();

		allLibrariesList.addAll(bookStores);
	}

	public ObservableList<BookStore> getAllLibrariesList() {
		return allLibrariesList;
	}

	public ObservableList<BookStore> getActiveLibrariesList() {
		return activeLibrariesList;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Bookstore");

		showBookStore();
	}

	public void showBookStore() {
		try {
			FXMLLoader loader = new FXMLLoader();
			URL url = getClass().getResource("/Libraries.fxml");
			loader.setLocation(url);


			rootLayout = loader.load();

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
