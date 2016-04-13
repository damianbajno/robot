package pl.bookstore.robot.bookssearch;

import com.jaunt.*;
import org.apache.log4j.Logger;
import pl.bookstore.robot.DAO.BookDAO;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class BookSearch {
    private Logger logger = org.apache.log4j.Logger.getLogger(BookSearch.class);

    private BookStore bookStore;
    private HashSet<String> linksSet;


    public BookSearch(BookStore bookStore, HashSet<String> links) {
        this.bookStore = bookStore;
        this.linksSet = links;
    }

    public BookSearch(BookStore bookStore) {
        this.bookStore = bookStore;
    }

    public static void main(String[] args) {
        try {
            BookSearch bookSearch = new BookSearch(new BookStore("Damian"));
            bookSearch.borixSearchBooks("http://www.bookrix.com/books.html");
        } catch (ResponseException e) {
            e.printStackTrace();
        } catch (NotFound e) {
            e.printStackTrace();
        }
    }



    public void borixSearchBooks(String link) throws ResponseException, NotFound {
        UserAgent userAgent = new UserAgent();
        Document visit = userAgent.visit(link);

        Elements bookElements = visit.findEvery("<div class=item-content>");

        Iterator<Element> bookIterator = bookElements.iterator();
        while (bookIterator.hasNext()) {
            Element bookElement = bookIterator.next();
            Element titlesElements = bookElement.findFirst("<a class=word-break>");

            Element categoryElements = bookElement.findFirst("<ul class=item-details>").findFirst("li");


            Book book = new Book(titlesElements.getText(), categoryElements.getText(), bookStore);
            BookDAO bookDAO = new BookDAO();
            bookDAO.persist(book);
        }
    }



//    public void borixSearchBooks(String link) throws ResponseException, NotFound {
//        UserAgent userAgent = new UserAgent();
//        Document visit = userAgent.visit(link);
//
//        Elements bookElements = visit.findEvery("<div class=item-content>");
//
//        Iterator<Element> bookIterator = bookElements.iterator();
//        while (bookIterator.hasNext()) {
//            Element bookElement = bookIterator.next();
//            Element titlesElements = bookElement.findFirst("<a class=word-break>");
//            Element categoryElements = bookElement.findFirst("<ul class=item-details>").findFirst("li");
//            Book book = new Book(titlesElements.getText(), categoryElements.getText(), bookStore);
//            BookDAO bookDAO = new BookDAO();
//            bookDAO.persist(book);
//        }


    private void create() {

    }


    @Override
    public String toString() {
        return "BookSearch [" + bookStore.toString() + "]";
    }

    private void searchBooks() throws IOException, ResponseException {


//			titles = userAgent.doc.findEvery("<p class=h2>").findEvery("<a>");
//			create();
//			titles = userAgent.doc.findEvery("<div class=product-name>").findEvery("<h2>").findEvery("<a>");
//			create();
//			titles = userAgent.doc.findEvery("<div class=emp-product-tile>").findEvery("<a>").findEach("<h3>");
//			create();
//			titles = userAgent.doc.findEach("<ul class=productslist>").findEvery("<li>").findEvery("<h3>")
//					.findEach("<a>");
//			create();
//			titles = userAgent.doc.findEvery("<article>").findEvery("<ul class=list has-ad-right>").findEvery("<div>")
//					.findEvery("<li>").findEvery("<a>");
//			create();
    }

}
