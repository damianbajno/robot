package pl.bookstore.robot.booksearch;

import com.jaunt.*;
import org.apache.log4j.Logger;
import pl.bookstore.robot.pojo.BookStore;
import pl.bookstore.robot.utils.UrlUtils;

import java.util.HashSet;

/**
 * Class was designed to search links in page
 * and sub pages specified in BookStore
 *
 * @author Damian Bajno (pseudo thread, Di)
 */

public class LinkSearch {
    private Logger logger = Logger.getLogger(LinkSearch.class);

    private HashSet<String> linksSet;
    private String mainPageAddress;
    private BookStore bookStore;

    /**
     * @param bookStore specify where to search books, and on what tags to search
     */
    public LinkSearch(BookStore bookStore) {
        this.bookStore = bookStore;
        linksSet = new HashSet<>();
        mainPageAddress = UrlUtils.getUrlToMainPage(bookStore.getUrl());
    }

    /**
     * @return set of links to page and sub pages found in BookStore
     */

    public HashSet<String> searchHyperlinksOnPageAndSubPages() {
        searchHyperLinksOnPage(bookStore.getUrl());
        logger.error("Site dose not found " + bookStore.getUrl());
        return linksSet;
    }

    /**
     * @param link search links in specified link
     * @return set of links found in link
     */

    HashSet<String> searchHyperLinksOnPage(String link) {
        try {
            logger.info("Searching links on page " + link);
            Document document = visitPageAndGetDocument(link);

            if (document!=null) searchHyperLinksOnPage(document);

        } catch (NotFound  e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return linksSet;
    }

    /**
     * @param document search links in specified link
     * @return set of links found in link
     */

    HashSet<String> searchHyperLinksOnPage(Document document) throws NotFound {

        if (document != null) {
            Elements aElementsOnSite = document.findEvery("<a href>");

            for (Element aElementOnSite : aElementsOnSite) {
                String hyperLink = aElementOnSite.getAt("href").toString();

                if (matchesConditions(hyperLink)) {
                    logger.info("Site added to LinksSet = " + hyperLink);
                    linksSet.add(hyperLink);

                    if (linksSet.size() < 3) searchHyperLinksOnPage(hyperLink);
                }
            }
        }
        return linksSet;
    }

    boolean matchesConditions(String hyperLink) {
        return hyperLink.contains(mainPageAddress) && !linksSet.contains(hyperLink) && !hyperLink.matches(".*\\.pdf");
    }

    final int CONNECTED_SUCCESSFUL = 200;

    Document visitPageAndGetDocument(String link) {
        Document document = null;
        try {
            UserAgent userAgent = new UserAgent();
            document = userAgent.visit(link);

            int status = userAgent.response.getStatus();
            if (status !=CONNECTED_SUCCESSFUL) document=null;

        } catch (ResponseException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return document;
    }

    /**
     * Method check if link to page is valid
     *
     * @return if url is valid
     */

    public boolean checkIfUrlToBookStoreIsValid(){
        return checkIfUrlIsValid(bookStore.getUrl());
    }

    private boolean checkIfUrlIsValid(String url) {
        int status = 0;
        try {
            UserAgent userAgent = new UserAgent();
            userAgent.visit(url);
            status = userAgent.response.getStatus();
        } catch (ResponseException e) {
            e.printStackTrace();
            logger.error(url+" status " +status);
        }
        return status == CONNECTED_SUCCESSFUL;
    }
}
