package pl.bookstore.robot.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.log4j.Logger;
import pl.bookstore.robot.dao.Dao;

import java.io.IOException;
import java.net.URL;

/**
 *
 * Start robot application.
 *
 * @author Damian Bajno
 */

public class GuiMainClass extends Application {
	private static Logger logger=Logger.getLogger(GuiMainClass.class);

	@Override
	public void start(Stage primaryStage) {
		logger.trace(GuiMainClass.class.toString()+"started");

		primaryStage.setTitle("Bookstore");

		try {
			URL javaFXCSS = getClass().getResource("/Libraries.fxml");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(javaFXCSS);

			Scene scene = new Scene(loader.load());

			primaryStage.setScene(scene);

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					Dao.closeSessionFactory();
					logger.trace("SessionFactory was closed");
				}
			});

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
