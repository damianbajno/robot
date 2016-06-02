package pl.bookstore.robot.booksearch;

import com.jaunt.Document;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.bookstore.robot.helper.BookStoreContainer;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.io.BufferedReader;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by damian on 6/1/16.
 */
public class BookParserWrapperTest {

    @DataProvider(name = "findBookOnDocument")
    public Object[][] dataProviderForSearchABookInDocument() {
        BookStore[] bookStores = BookStoreContainer.getBookStores();

        Object[][] data = {
                {bookStores[0], this.getHtmlPageBookBookrix(), new Book("New Life", "Romance")},
                {bookStores[1], this.getHtmlPageGoodreads(), new Book("Going Viral : The 9 Secrets of Irresistible Marketing", "brak")},
                {bookStores[2], this.getHtmlPagePwn(), new Book("Inwestycje", "INWESTYCJE")}
        };
        return data;
    }

    @Test(dataProvider = "findBookOnDocument")
    public void ifIPutDocumentWithBookItRetrieveBook(BookStore bookStore, String documentString, Book bookExpected) throws NotFound, ResponseException {
        //given
        Document document = new UserAgent().openContent(documentString);
        BookParserWrapper bookParserWrapper = new BookParserWrapper(bookStore);

        //when
        List<Book> books = bookParserWrapper.search(document);

        //then
        Assertions.assertThat(books.size()).isEqualTo(1);
        Assertions.assertThat(books).contains(bookExpected);
    }


    @Test
    public void getDocumentFromLinkIfLinkIsValid() throws ResponseException {
        //given
        BookStore searchedBookStore = BookStoreContainer.getBookStore(0);
        BookParserWrapper bookParserWrapper = new BookParserWrapper(searchedBookStore);


        //when
        Document receivedDocument = bookParserWrapper.getDocument(searchedBookStore.getUrl());

        //then
        Assertions.assertThat(receivedDocument.getRoot()).isNotNull();
    }

    @Test
    public void throwExceptionWhenTryToConnectToNotValidUrl() {
        //given
        BookStore searchedBookStore = BookStoreContainer.getBookStore(0);
        BookParserWrapper bookParserWrapper = new BookParserWrapper(searchedBookStore);

        //then
        Assertions.assertThatExceptionOfType(ResponseException.class).isThrownBy(() -> bookParserWrapper.getDocument("fdasasdfdsa"));
    }

    public static class DocumentReceiver{

        public static Document obtain(BookStore bookStore){
            URL url = ClassLoader.getSystemResource("BookStorePages/" + bookStore + ",html");
            BufferedReader bufferedReader = Files.newBufferedReader();
            return ;
        }
    }

    public String getHtmlPageBookBookrix() {
        String bookElement = new String("<body><div class=\"item-content\">\n" +
                "        <a class=\"item-author\" data-bxuid=\"hanax1\" href=\"/-hanax1/\">H.N. S</a>\n" +
                "        <big class=\"item-title\"><a class=\"word-break\" href=\"/_ebook-h-n-s-new-life/\">New Life </a></big>\n" +
                "        <ul class=\"item-details\">\n" +
                "            <li>Romance</li>\n" +
                "            <li>English</li>\n" +
                "            <li>46993 Words</li>\n" +
                "            <li>Ages 0 and up</li>\n" +
                "            <li><span class=\"item-details-views\" data-tooltip=\"\" title=\"820455 Views\">820455</span></li>\n" +
                "            <li><span class=\"item-details-favorites\" data-tooltip=\"\" title=\"4147 Favorites\">4147</span></li>\n" +
                "        </ul>\n" +
                "        <div class=\"item-description hyphenate\" lang=\"en\">\n" +
                "            <p>(Completed)\n" +
                "                Mia Owens and her mother have just moved to California from England to find a better life.\n" +
                "            </p><p>\n" +
                "            Mia just wants to live easy, go to school, hag out with friends... that was until Ian Marsh turned her life upside down.\n" +
                "        </p><p>\n" +
                "            Ian Marsh is rich and popular and can get wha...&nbsp;<a href=\"/_ebook-h-n-s-new-life/\">Read&nbsp;more...</a></p>                                                    </div>\n" +
                "        <p class=\"item-keywords word-break\"><strong>Keywords:</strong> <a href=\"/search;keywords:love,searchoption:books.html\">Love</a>, <a href=\"/search;keywords:romance,searchoption:books.html\">Romance</a>, <a href=\"/search;keywords:relationship,searchoption:books.html\">Relationship</a>, <a href=\"/search;keywords:rich,searchoption:books.html\">Rich</a>, <a href=\"/search;keywords:poor,searchoption:books.html\">Poor</a>, <a href=\"/search;keywords:school,searchoption:books.html\">School</a>, <a href=\"/search;keywords:job,searchoption:books.html\">Job</a>, <a href=\"/search;keywords:money,searchoption:books.html\">Money</a>, <a href=\"/search;keywords:sex,searchoption:books.html\">Sex</a></p>\n" +
                "        <p class=\"item-price\">\n" +
                "            <strong>\n" +
                "                For Free                                                            </strong>\n" +
                "        </p>\n" +
                "        <div class=\"item-action\">\n" +
                "            <a class=\"button small grey withIcon favorite open-login\" href=\"#\"><strong>Add to Favorites</strong></a>\n" +
                "        </div></body>");
        return bookElement;
    }

    public String getHtmlPageGoodreads() {
        String bookElement = new String("<div class=\"content370\">\n" +
                "    <div class=\"coverImage\">\n" +
                "      <a href=\"/book/show/29250939-going-viral\"><img alt=\"Going Viral  by Brent Coker\" title=\"Going Viral  by Brent Coker\" width=\"90\" class=\"bookCover\" src=\"https://d.gr-assets.com/books/1457798847m/29250939.jpg\"></a>\n" +
                "    </div>\n" +
                "    <div class=\"description descriptionContainer\">\n" +
                "      <a class=\"bookTitle\" href=\"https://www.goodreads.com/book/show/29250939-going-viral\">Going Viral : The 9 Secrets of Irresistible Marketing</a>\n" +
                "      <br>\n" +
                "      <span class=\"by smallText\">by</span>\n" +
                "<span itemprop=\"author\" itemscope=\"\" itemtype=\"http://schema.org/Person\">\n" +
                "<a class=\"authorName\" itemprop=\"url\" href=\"https://www.goodreads.com/author/show/15020625.Brent_Coker\"><span itemprop=\"name\">Brent Coker</span></a> <span class=\"greyText\">(Goodreads Author)</span>\n" +
                "</span>\n" +
                "\n" +
                "      <br>\n" +
                "        <div class=\"greyText releaseDate\">Release\n" +
                "          date: Mar 16, 2016</div>\n" +
                "      <div class=\"giveawayDescriptionDetails\">\n" +
                "        \n" +
                "<span id=\"freeTextContainer12397895291404083631\">Chance to win a first edition copy of Going Viral: The 9 secrets of irresistible marketing <br><br>\"For most of us, 'viral content' has become the modern day</span>\n" +
                "  <span id=\"freeText12397895291404083631\" style=\"display:none\">Chance to win a first edition copy of Going Viral: The 9 secrets of irresistible marketing <br><br>\"For most of us, 'viral content' has become the modern day Holy Grail--a nearly mythological thing that's always just outside of our grasp. Finally, here's a book that reveals the secrets about how to make the myth a reality. I can't wait to put these ideas into action.\"<br>--STEVE FARBER -AUTHOR, THE RADICAL LEAP,<br><br>“A fascinating deep dive into what drives us to share”<br>--PAMELA MEYER, CEO CALIBRATE, AND TOP 20 TED SPEAKER (11 MILLION VIEWS),<br><br>\"Brent's work will help you improve your odds substantially.\"<br>--'“THE AD MAN” RORY H SUTHERLAND -VICE CHAIRMAN OF OGILVY &amp; MATHER,<br><br>“One does not just go viral, one has to read Brent’s book first to understand the dynamics of becoming worthy of sharing.”<br>BRIAN SOLIS, -AUTHOR,<br></span>\n" +
                "  <a data-text-id=\"12397895291404083631\" href=\"#\" onclick=\"swapContent($(this));; return false;\">...more</a>\n" +
                "\n" +
                "        <br>\n" +
                "        <a class=\"actionLink detailsLink\" style=\"float: right\" href=\"/giveaway/show/180796-going-viral-the-9-secrets-of-irresistible-marketing\">View Details »</a>\n" +
                "      </div>\n" +
                "\n" +
                "    </div>\n" +
                "  </div>");
        return bookElement;
    }

    public String getHtmlPagePwn() {
        return "\n" +
                "            <div class=\"emp-product-offer-tags\">\n" +
                "    <span class=\"tags\"><a href=\"/promocje\" class=\"tag promotion\">PROMOCJA</a>\n" +
                "         <a href=\"/Bestsellery,65568768,h.html\" title=\"Zobacz wszystkie bestsellery\" class=\"tag bestseller\">BESTSELLER</a>\n" +
                "              \n" +
                "         \n" +
                "         \n" +
                "            \n" +
                "    </span>\n" +
                "</div>\n" +
                "<div class=\"emp-product-description\" itemprop=\"review\" itemscope=\"true\" itemtype=\"http://data-vocabulary.org/Review-aggregate\">\n" +
                "\n" +
                "\t\n" +
                "\n" +
                "\t<span class=\"name\" itemprop=\"itemReviewed\">Inwestycje</span><span class=\"name\" itemprop=\"itemReviewed\">Inwestycje</span><h1 itemprop=\"name\"><span class=\"type\">(Miękka)</span></h1>\n" +
                "\t<h2 class=\"emp-product-subName\">Instrumenty finansowe, aktywa niefinansowe, ryzyko finansowe, inżynieria finansowa</h2>\n" +
                "\t\n" +
                "\t<div class=\"rating\">\n" +
                "\t\t<div data-original-title=\"Oceń na: 4\" class=\"score clickable\" data-toggle=\"tooltip\" data-animation=\"false\" title=\"4\">\n" +
                "\t\t\t<span style=\"width: 97.4px;\" class=\"current-score rating-star\"></span>\n" +
                "\t\t</div>\n" +
                "\t\t<div class=\"score-text\">\n" +
                "\t\t\t<span itemprop=\"rating\">4.30</span>\n" +
                "\t\t\t<span>&nbsp;[</span>\n" +
                "\t\t\t<span itemprop=\"count\">7</span>\n" +
                "\t\t\t<span> ocen</span>\n" +
                "\t\t\t<span>]</span>\n" +
                "\t\t</div>&nbsp;<a href=\"#emp-product-reviews\" class=\"emp-check-review\" alt=\"Inwestycje - Teresa Jajuga, Krzysztof Jajuga - recenzje\" title=\"Inwestycje - Teresa Jajuga, Krzysztof Jajuga - recenzje\">Sprawdź recenzje</a>\n" +
                "\t</div>\n" +
                "\t<div class=\"collapse-button expand-button hidden-1170\">\n" +
                "\t\tRozwiń szczegóły »\n" +
                "\t</div>\n" +
                "\t<ul class=\"details hidden-480 hidden-768 collapse-div\">\n" +
                "\t\t\n" +
                "\t\t<li>\n" +
                "\t\t\t<h3>\n" +
                "\t\t\t\t<span class=\"key\">Wydanie:</span>\n" +
                "\t\t\t\t<span class=\"value\">Warszawa, </span><span class=\"value\">3, </span><span class=\"value\">2015</span>\n" +
                "\t\t\t</h3>\n" +
                "\t\t</li>\n" +
                "\t\t<li>\n" +
                "\t\t\t<h3>\n" +
                "\t\t\t\t<span class=\"key\">Seria / cykl:</span>\n" +
                "\t\t\t\t<span class=\"value\">\n" +
                "\t\t\t\t\t<a href=\"/seria/INWESTYCJE,s,74383389\" title=\"INWESTYCJE\">INWESTYCJE</a>\n" +
                "\t\t\t\t</span>\n" +
                "\t\t\t</h3>\n" +
                "\t\t</li>\n" +
                "\t\t<li>\n" +
                "\t\t\t<h2>\n" +
                "\t\t\t\t<span class=\"key\">Autor:</span>\n" +
                "\t\t\t\t<span class=\"value\">\n" +
                "\t\t\t\t\t<a href=\"/autor/Teresa-Jajuga,a,74089701\" title=\"Teresa Jajuga\">Teresa Jajuga</a><span>, </span><a href=\"/autor/Krzysztof-Jajuga,a,74089115\" title=\"Krzysztof Jajuga\">Krzysztof Jajuga</a>\n" +
                "\t\t\t\t</span>\n" +
                "\t\t\t</h2>\n" +
                "\t\t</li>\n" +
                "\t\t\n" +
                "\t\t\n" +
                "\t\t\n" +
                "\t\t\n" +
                "\t\t\n" +
                "\t\t\n" +
                "\t\t\n" +
                "\t\t<li>\n" +
                "\t\t\t<h3>\n" +
                "\t\t\t\t<span class=\"key\" itemprop=\"brand\">Wydawca:</span>\n" +
                "\t\t\t\t<span class=\"value\">\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t\t\t<a href=\"/wydawca/Wydawnictwo-Naukowe-PWN,w,69500989\" title=\"Wydawnictwo Naukowe PWN\">Wydawnictwo Naukowe PWN</a>\n" +
                "\t\t\t\t\t\n" +
                "\t\t\t\t</span>\n" +
                "\t\t\t</h3>\n" +
                "\t\t</li>\n" +
                "\t\t\n" +
                "\t\t\n" +
                "\t\t<li>\n" +
                "\t\t\t<h3>\n" +
                "\t\t\t\t<span class=\"key\">Typ oprawy:</span>\n" +
                "\t\t\t\t<span class=\"value\">miękka</span>\n" +
                "\t\t\t</h3>\n" +
                "\t\t</li>\n" +
                "\t\t\n" +
                "\t\t\n" +
                "\t</ul>\n" +
                "\t\n" +
                "\t\n" +
                "\t\n" +
                "\t<div class=\"collapse-button hidden hidden-1170\">\n" +
                "\t\tZwiń szczegóły »\n" +
                "\t</div>\n" +
                "\t<script type=\"text/javascript\">\n" +
                "\t\t/*<![CDATA[*/\n" +
                "\t\tempDocumentReady.push(function() {\n" +
                "\t\t\tempInitRating(\n" +
                "\t\t\t\t\t68736511,\n" +
                "\t\t\t\t\t4.3,\n" +
                "\t\t\t\t\ttrue);\n" +
                "\t\t});\n" +
                "\t\t/*]]>*/\n" +
                "\t</script>\n" +
                "</div>\n" +
                "        ";
    }

}
