package pl.bookstore.robot;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.log4j.Logger;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;

import pl.bookstore.robot.bookstorecontener.BookStore;
import pl.bookstore.robot.getbooks.BookSearche;
import pl.bookstore.robot.mysql.MySqlFunction;

public class Main {

	final static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args)

			throws NotFound, ResponseException, IOException, ClassNotFoundException, SQLException {
		if(args.length>=1)
		{
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
			if ( args[i].equals("gui")) {
				logger.info("Start GUI");
				System.out.println("GUI START");
			}else
			{
				logger.error("Nieznane polecenie");
			}
		}
		}else
		{			
			System.out.println("CLI START");
			logger.info("Start CLI");
			MySqlFunction mysql = new MySqlFunction();
			
			LinkedList<BookStore> book = mysql.getListBookStore();	
			
			mysql.saveListBookStore(book);
			mysql.close();
			for (BookStore bookStore : book) {
				if (bookStore.isActive())
					new BookSearche(bookStore.getUrl(), bookStore.getSiteName());
			}
			
		}
	}
}
