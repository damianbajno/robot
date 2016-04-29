package pl.bookstore.robot.booksearch;

import com.jaunt.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pl.bookstore.robot.helper.BookStoreContainer;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by damian on 14.04.16.
 */
public class BookSearchTest {

    @Test
    public void ifIPutDocumentWithBookItRetrieveBookBookixVer3() throws NotFound, ResponseException{
        //given
        BookStore bookStoreBookrix = BookStoreContainer.getBookStore(0);
        Document document= new UserAgent().openContent(this.getHtmlPageBookBookrix());
        Book bookExpected=new Book("New Life", "Romance", bookStoreBookrix);
        BookSearch bookSearch=new BookSearch(bookStoreBookrix);

        //when
        List<Book> books=bookSearch.searchBooksVer3(document);

        //then
        Assertions.assertThat(books).contains(bookExpected);
    }

    @Test
    public void ifIPutHtmlPageWithBookItRetrievesBookBookrix() throws NotFound, ResponseException{
        //given
        BookStore bookStoreBookrix = BookStoreContainer.getBookStore(0);
        Document document= new UserAgent().openContent(this.getHtmlPageBookBookrix());
        Book bookExpected=new Book("New Life", "Romance", bookStoreBookrix);
        BookSearch bookSearch=new BookSearch(bookStoreBookrix);

        //when
        List<Book> books = bookSearch.searchBooks(document);

        //then
        assertThat(books.size()).isGreaterThan(0);
        assertThat(books.get(0)).isEqualTo(bookExpected);
    }

    @Test
    public void ifIPutHtmlPageWithBookItRetrieveBookGoodReads() throws NotFound, ResponseException{
        //given
        BookStore bookStoreGoodreads = BookStoreContainer.getBookStore(1);
        Book bookExpected=new Book("Going Viral : The 9 Secrets of Irresistible Marketing", "brak", bookStoreGoodreads);

        Document document= new UserAgent().openContent(this.getHtmlPageGoodreads());
        BookSearch bookSearch=new BookSearch(bookStoreGoodreads);

        //when
        List<Book> books = bookSearch.searchBooks(document);

        //then
        assertThat(books.get(0)).isEqualTo(bookExpected);
    }

    @Test
    public void ifIPutHtmlPageWithBookItRetrieveBooksGutenberg() throws NotFound, ResponseException{
        //given
        BookStore bookStoreGutenberg = BookStoreContainer.getBookStore(2);
        Book bookExpected=new Book("Going Viral : The 9 Secrets of Irresistible Marketing", "brak", bookStoreGutenberg);

        Document document= new UserAgent().openContent(this.getHtmlPageGutenberg());
        BookSearch bookSearch=new BookSearch(bookStoreGutenberg);

        //when
        List<Book> books = bookSearch.searchBooks(document);

        //then
        assertThat(books.get(0)).isEqualTo(bookExpected);
    }

    @Test
    public void ifIPutHtmlPageWithBookItRetrievesBookBookrixVer1() throws NotFound, ResponseException{
        //given
        BookStore bookStoreBookrix = BookStoreContainer.getBookStore("Bookrix");
        Document document= new UserAgent().openContent(this.getHtmlPageBookBookrix());
        Book bookExpected=new Book("New Life", "Romance", bookStoreBookrix);
        BookSearch bookSearch=new BookSearch(bookStoreBookrix);

        //when
        List<Book> books = bookSearch.searchBooksVer1(document);

        //then
        assertThat(books.size()).isGreaterThan(0);
        assertThat(books.get(0)).isEqualTo(bookExpected);
    }

    @Test
    public void ifIPutHtmlPageWithBookItRetrieveBookGoodReadsVer1() throws NotFound, ResponseException{
        //given
        BookStore bookStoreGoodreads = BookStoreContainer.getBookStore(1);
        Book bookExpected=new Book("Going Viral : The 9 Secrets of Irresistible Marketing", "brak", bookStoreGoodreads);

        Document document= new UserAgent().openContent(this.getHtmlPageGoodreads());
        BookSearch bookSearch=new BookSearch(bookStoreGoodreads);

        //when
        List<Book> books = bookSearch.searchBooksVer1(document);

        //then
        assertThat(books.get(0)).isEqualTo(bookExpected);
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
                "        <p class=\"item-keywords word-break\"><strong>Keywords:</strong> <a href=\"/searchBooks;keywords:love,searchoption:books.html\">Love</a>, <a href=\"/searchBooks;keywords:romance,searchoption:books.html\">Romance</a>, <a href=\"/searchBooks;keywords:relationship,searchoption:books.html\">Relationship</a>, <a href=\"/searchBooks;keywords:rich,searchoption:books.html\">Rich</a>, <a href=\"/searchBooks;keywords:poor,searchoption:books.html\">Poor</a>, <a href=\"/searchBooks;keywords:school,searchoption:books.html\">School</a>, <a href=\"/searchBooks;keywords:job,searchoption:books.html\">Job</a>, <a href=\"/searchBooks;keywords:money,searchoption:books.html\">Money</a>, <a href=\"/searchBooks;keywords:sex,searchoption:books.html\">Sex</a></p>\n" +
                "        <p class=\"item-price\">\n"+
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

    public String getHtmlPageGutenberg() {
        return "";
    }
}
