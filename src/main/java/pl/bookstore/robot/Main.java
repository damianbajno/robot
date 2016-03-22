package pl.bookstore.robot;

import java.io.IOException;

import org.apache.log4j.Logger;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;

import getbooks.BookSearche;

public class Main {

	final static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws NotFound, ResponseException, IOException {

		new BookSearche("http://ekiosk24.nextore.pl/", "ekiosk");

		new BookSearche("http://www.gandalf.com.pl/", "gandalf");

		new BookSearche("http://ksiegarnia.pwn.pl/", "pwn");

		new BookSearche("http://www.matras.pl/", "matras");

		new BookSearche("http://helion.pl", "helion");

	}
}
