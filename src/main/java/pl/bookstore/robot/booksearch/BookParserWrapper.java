package pl.bookstore.robot.booksearch;

import com.jaunt.*;
import org.apache.log4j.Logger;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damian on 6/1/16.
 */
public class BookParserWrapper extends ParserWrapper {
    private Logger logger=Logger.getLogger(BookParserWrapper.class);
    private BookStore bookStore;

    public BookParserWrapper(BookStore bookStore) {
        this.bookStore = bookStore;
    }

    /**
     * Search books in Jaunt Document
     *
     * @param document
     * @return list of books found in document
     */
    List<Book> search(Document document) {
        ArrayList<Book> books = new ArrayList<>();

        List<String> pathToTitleElement = BookParserUtils.getPathToElement(bookStore.getSearchForTitle());
        List<String> pathToCategoryElement = BookParserUtils.getPathToElement(bookStore.getSearchForCategory());

        Elements titleElements = document.findEvery(pathToTitleElement.get(1));
        Elements categoryElements = document.findEvery(pathToCategoryElement.get(1));

        String title = null;
        String previousTitle = "";
        for (int i = 0; i < titleElements.size(); i++) {
            Book book = null;
            String category = "brak";
            try {
                title = searchElement(titleElements.getElement(i), pathToTitleElement);
                category = searchElement(categoryElements.getElement(i), pathToCategoryElement);
            } catch (NotFound e) {
                logger.trace("Did not find any Books on site " + document.getUrl());
            } finally {
                if (!previousTitle.equals(title)) {
                    book = new Book(title, category, bookStore);
                    books.add(book);
                    logger.trace("Finded book " + book);
                }
                previousTitle = title;
            }

        }
        return books;
    }

    private String searchElement(Element element, List<String> pathToElement) {
        try {
            for (int i = 2; i < pathToElement.size(); i = i + 2) {
                Elements pathToElement1 = element.findEach(pathToElement.get(i + 1));
                element = pathToElement1.getElement(Integer.parseInt(pathToElement.get(i)) - 1);
            }
        } finally {
            return element.getText().trim();
        }
    }


}
