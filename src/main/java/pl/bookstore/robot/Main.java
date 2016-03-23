package pl.bookstore.robot;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;

import bookstorecontener.BookStore;
import getbooks.BookSearche;
import mysql.MySqlFunction;

public class Main {

	final static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args)
			throws NotFound, ResponseException, IOException, ClassNotFoundException, SQLException {
		MySqlFunction mysql = new MySqlFunction();
		mysql.dropTableToNewSave();
		mysql.saveListBookStore("http://ekiosk24.nextore.pl/", "ekiosk", true);
		mysql.saveListBookStore("http://www.gandalf.com.pl/", "gandalf", false);
		mysql.saveListBookStore("http://ksiegarnia.pwn.pl/", "pwn", true);
		mysql.saveListBookStore("http://www.matras.pl/", "matras", false);
		mysql.saveListBookStore("http://helion.pl", "helion", false);

		LinkedList<BookStore> book;
		book = mysql.getListBookStore();
		for (BookStore bookStore : book) {
			System.out.println(bookStore.getSiteName());
			System.out.println(bookStore.getUrl());
			System.out.println(bookStore.isIsActive());
		}
		mysql.close();

		for (BookStore bookStore : book) {
			if (bookStore.isIsActive())
				new BookSearche(bookStore.getUrl(), bookStore.getSiteName());
		}

	}
}
