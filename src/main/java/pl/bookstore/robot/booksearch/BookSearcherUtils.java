package pl.bookstore.robot.booksearch;


import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Damian Bajno
 *
 * Created by damian on 4/28/16.
 */
class BookSearcherUtils {

    /**
     * Check provided path to searched text.
     *
     * @param givenPathToSearch search path
     * @return Corrected path to element
     */
    static List<String> getPathToElement(String givenPathToSearch) {
        String[] tableWithPath = givenPathToSearch.split("[<>]");

        List<String> listWithPath = new ArrayList<String>();

        switchFromTableToArray(tableWithPath, listWithPath);

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

    private static void switchFromTableToArray(String[] array, List<String> list) {
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
    }
}