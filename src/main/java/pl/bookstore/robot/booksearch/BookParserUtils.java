package pl.bookstore.robot.booksearch;


import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * @author Damian Bajno
 *
 * Created by damian on 4/28/16.
 */
class BookParserUtils {

    /**
     * Check provided path to searched text.
     *
     * @param givenPathToSearch search path
     * @return Corrected path to element
     */
    static List<String> getPathToElement(String givenPathToSearch) {
        String[] tableWithPath = givenPathToSearch.split("[<>]");

        List<String> listWithPath=Arrays.asList(tableWithPath);

        for (int i = 0; i < listWithPath.size(); i++) {
            String element = listWithPath.get(i);

            if (!NumberUtils.isNumber(element) && (i % 2 == 0)) {
                listWithPath.set(i, "1");
                continue;
            }

            if (!NumberUtils.isNumber(element))
                listWithPath.set(i, "<" + element + ">");
        }

        return listWithPath;
    }


}