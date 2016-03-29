package pl.bookstore.robot.getbooks;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;

import pl.bookstore.robot.logowanie.Logowanie;

public class BookSearche extends LinkSearch {

	private String nameBookStory;
	private Elements titles;
	private LinkedList<String> listBook;

	public BookSearche(String urlBookStory, String nameBookStory) throws ResponseException, NotFound, IOException {
		super(urlBookStory);
		this.nameBookStory = nameBookStory;
		listBook = new LinkedList<String>();
		searchBooks();
	}

	private void searchBooks() throws IOException, ResponseException {
		for (Iterator<String> iterator = correctLink.iterator(); iterator.hasNext();) {
			String element = iterator.next();
			userAgent.visit(element);

			titles = userAgent.doc.findEvery("<p class=h2>").findEvery("<a>");
			create();
			titles = userAgent.doc.findEvery("<div class=product-name>").findEvery("<h2>").findEvery("<a>");
			create();
			titles = userAgent.doc.findEvery("<div class=emp-product-tile>").findEvery("<a>").findEach("<h3>");
			create();
			titles = userAgent.doc.findEach("<ul class=productslist>").findEvery("<li>").findEvery("<h3>")
					.findEach("<a>");
			create();
			titles = userAgent.doc.findEvery("<article>").findEvery("<ul class=list has-ad-right>").findEvery("<div>")
					.findEvery("<li>").findEvery("<a>");
			create();
		}
		userAgent.close();
	}

	private void create() {

		for (Element title : titles) {
			new Logowanie(title.getText(), this.toString());
			listBook.add(title.getText());
		}
	}

	public LinkedList<String> getListBook() {
		return listBook;
	}

	@Override
	public String toString() {
		return "BookSearche [" + nameBookStory + "]";
	}

}
