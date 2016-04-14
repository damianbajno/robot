package pl.bookstore.robot.booksearch;

import com.jaunt.*;
import org.testng.annotations.Test;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by damian on 14.04.16.
 */
public class BookSearchTest {

    @Test
    public void ifIPutHtmlPageWithBookItRetriveBook() throws NotFound, ResponseException{
        //given
        BookStore bookStoreBoorix = new BookStore("boorix", "http://www.bookrix.com/books.html", "<div class=\"item-content\">", "<a class=word-break>", "<ul class=item-details>" + "<li>");
        Document document= new UserAgent().openContent(this.getHtmlPage());
        Book bookExpected=new Book("New Life", "Romance", bookStoreBoorix);
        BookSearch bookSearch=new BookSearch(bookStoreBoorix);

        //when
        List<Book> findedBooks = bookSearch.searchBooks(document);

        //then
        assertThat(findedBooks.get(0)).isEqualTo(bookExpected);
    }

    public String getHtmlPage() {
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
}
