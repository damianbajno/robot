package pl.bookstore.robot.booksearch;

import com.jaunt.*;
import org.apache.log4j.Logger;
import pl.bookstore.robot.pojo.BookStore;
import pl.bookstore.robot.utils.UrlUtils;

import java.util.HashSet;
import java.util.Iterator;

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
        try {
            searchHyperlinksOnSite(bookStore.getUrl());
        } catch (NotFound notFound) {
            logger.error("Site dosen't found " + bookStore.getUrl());
        }
        return linksSet;
    }

    protected HashSet<String> searchHyperlinksOnSite(String link) throws NotFound {
        Document document = visitPageAndGetDocument(link);

        searchHyperLinkOnPage(document);
        return linksSet;
    }

    protected HashSet<String> searchHyperLinkOnPage(Document document) throws NotFound {
        if (document != null) {
            Elements aElementsOnSite = document.findEvery("<a href>");

            for (Element aElementOnSite : aElementsOnSite) {
                String hyperLink = aElementOnSite.getAt("href").toString();

                if (matchesConditions(hyperLink)) {
                    logger.info("Site = " + hyperLink);
                    linksSet.add(hyperLink);

                    if (linksSet.size()<10) searchHyperlinksOnSite(hyperLink);
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
            UserAgent userAgent = new UserAgent();
            document = userAgent.visit(link);
        } catch (ResponseException e) {
            e.printStackTrace();
        }
        return document;
    }

    public HashSet<String> getHyperLinks(){
        return linksSet;

    }
}
