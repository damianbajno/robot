package pl.bookstore.robot.booksearch;

import com.jaunt.Document;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import org.apache.log4j.Logger;
import pl.bookstore.robot.pojo.BookStore;
import pl.bookstore.robot.utils.UrlUtils;

import java.util.HashSet;

/**
 * Created by damian on 6/1/16.
 */
public class LinkParserWrapper extends ParserWrapper {
    private Logger logger = Logger.getLogger(LinkParserWrapper.class);

    private String mainPageAddress;
    private BookStore bookStore;


    public LinkParserWrapper(BookStore bookStore) {
        this.bookStore = bookStore;
        this.mainPageAddress = UrlUtils.getUrlToMainPage(bookStore.getUrl());
    }

    /**
     * @param document search links in specified link
     * @return set of links found in link
     */
    HashSet<String> search(Document document) throws NotFound {
        HashSet<String> linksSet = new HashSet<>();
        Elements aElements = document.findEvery("<a href>");

        for (Element aElement : aElements) {
            String hyperLink = aElement.getAt("href").toString();

            if (matchesConditions(hyperLink)) {
                logger.trace("Site added to search for books = " + hyperLink);
                linksSet.add(hyperLink);
            }
        }
        return linksSet;
    }


    private boolean matchesConditions(String hyperLink) {
        return hyperLink.contains(mainPageAddress) && !hyperLink.matches(".*\\.pdf");
    }

}
