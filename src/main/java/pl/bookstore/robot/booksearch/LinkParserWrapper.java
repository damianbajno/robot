package pl.bookstore.robot.booksearch;

import com.jaunt.Document;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import org.apache.log4j.Logger;
import pl.bookstore.robot.pojo.BookStore;
import pl.bookstore.robot.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damian on 6/1/16.
 */
public class LinkParserWrapper {
    private Logger logger = Logger.getLogger(LinkParserWrapper.class);

    private String mainPageAddress;


    public LinkParserWrapper(BookStore bookStore) {
        this.mainPageAddress = UrlUtils.getUrlToMainPage(bookStore.getUrl());
    }

    /**
     * @param document searchRecursivelyIn links in specified link
     * @return set of links found in link
     */
    public List<String> searchIn(Document document) {
        List<String> linkList = new ArrayList<>();

        try {
            Elements aElements = document.findEvery("<a href>");

            for (Element aElement : aElements) {
                String hyperLink = aElement.getAt("href").toString();

                if (matchesConditions(hyperLink)) {
                    logger.trace("Site added to searchRecursivelyIn for books = " + hyperLink);
                    linkList.add(hyperLink);
                }
            }
            return linkList;
        } catch (NotFound notFound) {
            notFound.printStackTrace();
            return new ArrayList<>();
        }

    }

    private boolean matchesConditions(String hyperLink) {
        return hyperLink.contains(mainPageAddress) && !hyperLink.matches(".*\\.pdf");
    }

}
