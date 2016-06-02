package pl.bookstore.robot.booksearch;

import com.jaunt.Document;
import com.jaunt.ResponseException;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by damian on 6/2/16.
 */
public class BookSearcher {
    private HashSet<String> linkSet = new HashSet<>();

    private LinkParserWrapper linkParserWrapper;
    private BookParserWrapper bookParserWrapper;


    public BookSearcher(BookStore bookStore) {
        this.linkParserWrapper = new LinkParserWrapper(bookStore);
        this.bookParserWrapper = new BookParserWrapperImpl(bookStore);
    }

    public List<Book> searchRecursivelyIn(List<String> linkList) {
        List<Book> bookList = new ArrayList<>();

        linkList.stream().forEach(link -> {

                    if (!linkSet.contains(link)) {
                        linkSet.add(link);

                        try {
                            Document document = DocumentWrapper.getDocumentFrom(link);
                            bookList.addAll(bookParserWrapper.searchIn(document));
                            List<String> linkPageList = linkParserWrapper.searchIn(document);

                            if (linkSet.size() < 5 && linkPageList.size() < 20) searchRecursivelyIn(linkPageList);

                        } catch (ResponseException e) {
                            e.printStackTrace();
                        }

                    }
                }
        );
        return bookList;
    }
}
