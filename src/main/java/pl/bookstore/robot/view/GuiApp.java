package pl.bookstore.robot.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;

public class GuiApp extends Application {
	Logger logger=Logger.getLogger(GuiApp.class);

	@Override
	public void start(Stage primaryStage) {
		logger.info(GuiApp.class.toString()+"started");
		primaryStage.setTitle("Bookstore");

		try {
			URL javaFXCSS = getClass().getResource("/Libraries.fxml");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(javaFXCSS);

			Scene scene = new Scene(loader.load());

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
