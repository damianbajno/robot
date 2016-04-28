package pl.bookstore.robot.utils;

import org.assertj.core.api.Assertions;
import org.assertj.core.internal.Arrays;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damian on 4/28/16.
 */
public class BookSearchUtilTest {

    @Test
    public void convertFromStringToTablePathToElementWhenEveryFieldIsGiven() {
        //given
        String givenPath = "1<dsafsafsdaf>";
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("1");
        expectedPath.add("<dsafsafsdaf>");

        //when
        List resultPath = BookSearchUtils.getPathToElementInTable(givenPath);

        //then
        Assertions.assertThat(resultPath).isEqualTo(expectedPath);
    }

    @Test
    public void convertFromStringToTablePathToElementWhenNotEveryFieldIsGiven() {
        //given
        String givenPathToSearch = "<dsafsafsdaf>";
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("1");
        expectedPath.add("<dsafsafsdaf>");


        //when
        List resultPath = BookSearchUtils.getPathToElementInTable(givenPathToSearch);

        //then
        Assertions.assertThat(resultPath).isEqualTo(expectedPath);
    }

    @Test
    public void convertFromStringToTablePathToSearchElementWhenNotEveryFieldIsGivenTwoPathLong() {
        //given
        String givenPathToSearch = "<dsafsafsdaf><dsaffdsfa324af>";
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("1");
        expectedPath.add("<dsafsafsdaf>");
        expectedPath.add("1");
        expectedPath.add("<dsaffdsfa324af>");

        //when
        List resultPath = BookSearchUtils.getPathToElementInTable(givenPathToSearch);

        //then
        Assertions.assertThat(resultPath).isEqualTo(expectedPath);
    }

    @Test
    public void convertFromStringToTablePathToSearchElementWhenEveryFieldIsGivenTwoPathLong() {
        //given
        String givenPathToSearch = "2<dsafsafsdaf>1<dsaffdsfa324af>";
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("2");
        expectedPath.add("<dsafsafsdaf>");
        expectedPath.add("1");
        expectedPath.add("<dsaffdsfa324af>");
        expectedPath.
        //when
        List resultPath = BookSearchUtils.getPathToElementInTable(givenPathToSearch);

        //then
        Assertions.assertThat(resultPath).isEqualTo(expectedPath);
    }

    @DataProvider(name = "Paths")
    public Object[][] givenAndExpectedPaths(){

        return "";
    }


    @Test(groups = "NewTest", dataProvider = "Paths")
    public void convertFromStringToTablePathToSearchElement(String givenPath, String expectedPath) {

        //when
        List resultPath = BookSearchUtils.getPathToElementInTable(givenPath);

        //then
        Assertions.assertThat(resultPath).isEqualTo(expectedPath);
    }

}
