package getbooks;


import java.util.Arrays;
import java.util.HashSet;

import com.jaunt.*;

public class Ekiosk24 {
	private final static HashSet<String> wordToFindsLinks = new HashSet<String>(Arrays.asList("darmowe"));
	private UserAgent userAgent;
	private String urlBookStore = null;
	private Elements linkOnSite;
	private Elements titles;
	private String allElement = "";

	public Ekiosk24() throws ResponseException, NotFound {
		userAgent = new UserAgent();
		urlBookStore = "http://ekiosk24.nextore.pl/";
		userAgent.visit(urlBookStore);
		linkOnSite = userAgent.doc.findEvery("a");
		pwdSearch();
	}

	public Ekiosk24(String url) throws ResponseException, NotFound {
		userAgent = new UserAgent();
		urlBookStore = url;
		userAgent.visit(urlBookStore);
		linkOnSite = userAgent.doc.findEvery("a");
		pwdSearch();
	}

	private void pwdSearch() throws NotFound, ResponseException {
		for (Element link : linkOnSite) {
			if (wordToFindsLinks.contains(link.getText())) {
				userAgent.visit(link.getAt("href"));
				titles = userAgent.doc.findEvery("<li>").findEvery("<h3>").findEach("<a>");
				for (Element title : titles)
					allElement = allElement + title.getText() + "\n";
			}
		}
	}
	@Override
	public String toString() {
		return "Ekiosk24 [allElement=\n" + allElement + "]";
	}

}
