package pl.bookstore.robot.booksearch;


import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class with useful tools for BookSearch
 *
 *
 * @author Damian Bajno
 *
 * Created by damian on 4/28/16.
 */
class BookSearcherUtils {

    /**
     *
     * Check provided path to searched text.
     *
     * @param givenPathToSearch search path
     * @return Corrected path to element
     */

    public static List<String> getPathToElement(String givenPathToSearch) {
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

    /**
     *
     * Method to switch from array to list.
     *
     * @param array data to put in list
     * @param list where are added elements from array
     */

    private static void switchFromTableToArray(String[] array, List<String> list) {
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
    }

}