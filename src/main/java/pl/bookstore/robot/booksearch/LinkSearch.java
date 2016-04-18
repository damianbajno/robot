package pl.bookstore.robot.booksearch;

import com.jaunt.*;
import org.apache.log4j.Logger;
import pl.bookstore.robot.pojo.BookStore;
import pl.bookstore.robot.utils.UrlUtils;

import java.util.HashSet;

public class LinkSearch {
    private Logger logger = Logger.getLogger(LinkSearch.class);

    private HashSet<String> linksSet;
    private String mainPageAdress;
    private BookStore bookStore;

    public LinkSearch(BookStore bookStore) {
        this.bookStore = bookStore;
        linksSet = new HashSet<>();
        mainPageAdress = UrlUtils.getUrlToMainPage(bookStore.getUrl());
    }

    public HashSet<String> searchHyperlinksOnSiteAndSubsites() {
        searchHyperLinksOnSite(bookStore.getUrl());
        logger.error("Site dosen't found " + bookStore.getUrl());
        return linksSet;
    }

    HashSet<String> searchHyperLinksOnSite(String link) {
        try {
            logger.info("Searching Links on page " + link);
            Document document = visitPageAndGetDocument(link);
            searchHyperLinksOnPage(document);
        } catch (NotFound notFound) {
            notFound.printStackTrace();
            logger.error(notFound.getMessage());
        }
        return linksSet;
    }

    HashSet<String> searchHyperLinksOnPage(Document document) throws NotFound {
        if (document != null) {
            Elements aElementsOnSite = document.findEvery("<a href>");

            for (Element aElementOnSite : aElementsOnSite) {
                String hyperLink = aElementOnSite.getAt("href").toString();

                if (matchesConditions(hyperLink)) {
                    logger.info("Site added to LinksSet = " + hyperLink);
                    linksSet.add(hyperLink);

                    if (linksSet.size() < 10) searchHyperLinksOnSite(hyperLink);
                }
            }
        }
        return linksSet;
    }

    boolean matchesConditions(String hyperLink) {
        return hyperLink.contains(mainPageAdress) && !linksSet.contains(hyperLink) && !hyperLink.matches(".*\\.pdf");
    }

    private Document visitPageAndGetDocument(String link) {
        Document document = null;
        try {
            document = new UserAgent().visit(link);
        } catch (ResponseException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return document;
    }

    final int CONNECTED_SUCCESSFUL = 200;

    public boolean checkUrl() throws ResponseException {
        int status = 0;
        String url=null;
        try {
            url = bookStore.getUrl();
            HttpResponse httpResponse = new UserAgent().sendHEAD(url);
            status = httpResponse.getStatus();
        } catch (ResponseException e) {
            e.printStackTrace();
            logger.error(url+" status " +status);
        }
        return status == CONNECTED_SUCCESSFUL;
    }
}
