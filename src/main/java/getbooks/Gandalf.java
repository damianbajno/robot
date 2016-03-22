package getbooks;

import java.util.Arrays;
import java.util.HashSet;

import com.jaunt.*;

public class Gandalf {
	private final static HashSet<String> wordToFindsLinks = new HashSet<String>(Arrays.asList("Promocje"));
	private UserAgent userAgent;
	private String urlBookStore = null;
	private Elements linkOnSite;
	private Elements titles;
	private String allElement = "";

	public Gandalf() throws ResponseException, NotFound {
		userAgent = new UserAgent();
		urlBookStore = "http://www.gandalf.com.pl/";
		userAgent.visit(urlBookStore);
		linkOnSite = userAgent.doc.findEvery("a");
		pwdSearch();
	}

	public Gandalf(String url) throws ResponseException, NotFound {
		userAgent = new UserAgent();
		urlBookStore = url;
		userAgent.visit(urlBookStore);
		linkOnSite = userAgent.doc.findEvery("a");
		pwdSearch();
	}

	private void pwdSearch() throws NotFound, ResponseException {
		for (Element link : linkOnSite) {
			if (wordToFindsLinks.contains(link.getText()) && (link.getAt("href").toString()).contains("podreczniki")) {
				userAgent.visit(link.getAt("href"));
				titles =userAgent.doc.findEvery("<p class=h2>").findEvery("<a>");
				for (Element title : titles)
					allElement = allElement + title.getText() + "\n";
			}
		}
	}

	@Override
	public String toString() {
		return "Gandalf [allElement=\n" + allElement + "]";
	}

}
