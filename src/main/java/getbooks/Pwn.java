package getbooks;

import java.util.Arrays;
import java.util.HashSet;

import com.jaunt.*;

public class Pwn {
	private final static HashSet<String> wordToFindsLinks = new HashSet<String>(Arrays.asList("PROMOCJE"));
	private UserAgent userAgent;
	private String urlBookStore = null;
	private Elements linkOnSite;
	private Elements titles;
	private String allElement = "";

	public Pwn() throws ResponseException, NotFound {
		userAgent = new UserAgent();
		urlBookStore = "http://ksiegarnia.pwn.pl/";
		userAgent.visit(urlBookStore);
		linkOnSite = userAgent.doc.findEvery("a");
		pwdSearch();
	}

	public Pwn(String url) throws ResponseException, NotFound {
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
				titles = userAgent.doc.findEvery("<div class=emp-product-tile>").findEvery("<a>").findEach("<h3>");
				for (Element title : titles)
					allElement = allElement + title.getText() + "\n";
			}

		}

	}
	@Override
	public String toString() {
		return "Pwd [allElement=\n" + allElement + "]";
	}

}
