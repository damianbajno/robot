package pl.bookstore.robot.booksearch;

import com.jaunt.*;
import org.apache.log4j.Logger;
import pl.bookstore.robot.booksearch.wrapper.LinkParserWrapper;
import pl.bookstore.robot.pojo.BookStore;

import java.util.HashSet;

/**
 * Search links in page and sub pages specified BookStore
 *
 * @author Damian Bajno (pseudo thread, Di)
 */
public class LinkParserCollector {
    private Logger logger = Logger.getLogger(LinkParserCollector.class);

    private BookStore bookStore;
    private LinkParserWrapper linkParserWrapper;
    private HashSet<String> allLinks = new HashSet<>();

    /**
     * @param bookStore specify where to search books, and paths to searched elements
     */
    public LinkParserCollector(BookStore bookStore) {
        this.linkParserWrapper = new LinkParserWrapper(bookStore);
    }

    /**
     * @return set of links found in link
     */
//    HashSet<String> search() {
//        HashSet<String> linksSet = new HashSet<>();

//        try {
//            Document document = linkParserWrapper.getDocumentFrom(bookStore.getUrl());
//            HashSet<String> linkSet = linkParserWrapper.search(document);
//            allLinks.addAll(linkSet);
//            searchRecursively(linkSet);
//        } catch (NotFound | ResponseException e) {
//            e.printStackTrace();
//            logger.error(e.getMessage());
//        }
//        return linksSet;
//    }
//
//    private void searchRecursively(HashSet<String> linkSet) throws NotFound, ResponseException {
//        for (String link : linkSet) {
//            Document document = linkParserWrapper.getDocumentFrom(link);
//            HashSet<String> linkSetL = linkParserWrapper.search(document);
//            allLinks.addAll(linkSetL);
//            if (allLinks.size() < 5) searchRecursively(linkSetL);
//        }
//    }

}
