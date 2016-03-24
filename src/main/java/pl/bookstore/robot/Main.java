package pl.bookstore.robot;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;

import pl.bookstore.robot.bookstorecontener.BookStore;
import pl.bookstore.robot.getbooks.BookSearche;
import pl.bookstore.robot.mysql.MySqlFunction;
 

public class Main {

	final static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args)

	{
		if (args.length >= 1) {
			for (int i = 0; i < args.length; i++) {
				System.out.println(args[i]);
				if (args[i].equals("gui")) {
					logger.info("Start GUI");
					System.out.println("GUI START");
				} else {
					logger.error("Nieznane polecenie");
				}
			}
		} else {
			System.out.println("CLI START");
			logger.info("Start CLI");
			MySqlFunction mysql = null;
			try {
				mysql = new MySqlFunction();
			} catch (ClassNotFoundException | SQLException e) {
				logger.error(e);
				e.printStackTrace();
			}

			LinkedList<BookStore> book = null;
			try {
				book = mysql.getListBookStore();
			} catch (SQLException e) {
				logger.error(e);
				e.printStackTrace();
			}

			try {
				mysql.saveListBookStore(book);
			} catch (SQLException e) {
				logger.error(e);
				e.printStackTrace();
			}
			mysql.close();
			for (BookStore bookStore : book) {
				if (bookStore.isActive())
					try {
						new BookSearche(bookStore.getUrl(), bookStore.getSiteName());
					} catch (NotFound | ResponseException | IOException e) {
						logger.error(e);
					}
			}

		}
	}
}
