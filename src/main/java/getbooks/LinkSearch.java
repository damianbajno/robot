package getbooks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import searchpattern.KPPattern;

public class LinkSearch {

	private final static ArrayList<String> wordInLinkToFindsLinks = new ArrayList<String>(
			Arrays.asList("darmowe-ebooki", "darmowe", "Promocj", "promocje", "PROMOCJE", "OUTLET"));

	private final static ArrayList<String> wordInLinkToFindsLinksInCorrect = new ArrayList<String>(
			Arrays.asList("audiobooki", "perfumy", "audio", "puzzle", "pomoc", "eksiazki.az.pl", "e-prasa", "Artykul"));

	protected HashSet<String> correctLink;
	protected UserAgent userAgent;
	private Elements linkOnSite;

	public LinkSearch(String url) throws ResponseException, NotFound, IOException {
		userAgent = new UserAgent();
		correctLink = new HashSet<>();
		userAgent.visit(url);
		linkOnSite = userAgent.doc.findEvery("<a href>");
		searchAllLink();
	}

	private void searchAllLink() throws NotFound, IOException {
		for (Element link : linkOnSite) {
			String linkUrl = link.getAt("href").toString();
			for (String keyWord : wordInLinkToFindsLinks) {
				if (new KPPattern().KP(linkUrl, keyWord) || new KPPattern().KP(link.getText(), keyWord)) {
					correctLink.add(linkUrl);
				}
			}
		}
		for (Iterator<String> iterator = correctLink.iterator(); iterator.hasNext();) {
			String linkUrl = iterator.next();
			for (int j = 0; j < wordInLinkToFindsLinksInCorrect.size(); j++) {
				if (new KPPattern().KP(linkUrl, wordInLinkToFindsLinksInCorrect.get(j))) {
					iterator.remove();
					break;
				}
			}
		}
	}
}
