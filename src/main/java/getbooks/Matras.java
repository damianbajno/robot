package getbooks;


import java.util.Arrays;
import java.util.HashSet;

import com.jaunt.*;

public class Matras {
	private final static HashSet<String> wordToFindsLinks = new HashSet<String>(Arrays.asList("Promocje"));
	private UserAgent userAgent;
	private String urlBookStore = null;
	private Elements linkOnSite;
	private Elements titles;
	private String allElement = "";

	public Matras() throws ResponseException, NotFound {
		userAgent = new UserAgent();
		urlBookStore = "http://www.matras.pl/";
		userAgent.visit(urlBookStore);
		linkOnSite = userAgent.doc.findEvery("a");
		pwdSearch();
	}

	public Matras(String url) throws ResponseException, NotFound {
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
			 titles = userAgent.doc.findEvery("<h2>").findEvery("<a>");
				for (Element title : titles)
					allElement = allElement + title.getText() + "\n";
			}

		}

	}
	@Override
	public String toString() {
		return "Matras [allElement=\n" + allElement + "]";
	}

}
