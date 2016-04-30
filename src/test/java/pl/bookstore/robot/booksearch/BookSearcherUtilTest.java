package pl.bookstore.robot.booksearch;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.bookstore.robot.utils.PathArrayList;

import java.util.List;

/**
 * Created by damian on 4/28/16.
 */
public class BookSearcherUtilTest {

    @DataProvider(name = "Paths")
    public Object[][] givenAndExpectedPaths() {
        Object[][] paths =
                {{"1<dsafsafsdaf>", new PathArrayList().addElements("1", "<dsafsafsdaf>")},
                        {"<dsafsafsdaf>", new PathArrayList().addElements("1", "<dsafsafsdaf>")},
                        {"<dsafsafsdaf><dsaffdsfa324af>", new PathArrayList().addElements("1", "<dsafsafsdaf>", "1", "<dsaffdsfa324af>")},
                        {"1<dsafsafsdaf>2<dsaffdsfa324af>", new PathArrayList().addElements("1", "<dsafsafsdaf>", "2", "<dsaffdsfa324af>")},
                        {"<brak", new PathArrayList().addElements("1", "<brak>")}};
        return paths;
    }


    @Test(dataProvider = "Paths")
    public void convertFromStringToTablePathToSearchElement(String givenPath, List expectedPath) {

        //when
        List resultPath = BookSearcherUtils.getPathToElement(givenPath);

        //then
        Assertions.assertThat(resultPath).isEqualTo(expectedPath);
    }


}
