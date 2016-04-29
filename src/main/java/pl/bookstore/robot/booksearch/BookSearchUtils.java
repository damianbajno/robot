package pl.bookstore.robot.booksearch;


import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by damian on 4/28/16.
 */
class BookSearchUtils {

    public static List<String> getPathToElement(String givenPathToSearch) {
        String[] tableWithPath = givenPathToSearch.split("[<>]");

        List<String> tableWithPathList = new ArrayList<String>();

        for (int i = 0; i < tableWithPath.length; i++) {
            tableWithPathList.add(tableWithPath[i]);
        }

        for (int i = 0; i < tableWithPathList.size(); i++) {
            String element = tableWithPathList.get(i);

            if (!NumberUtils.isNumber(element) & (i % 2 == 0)) {
                tableWithPathList.set(i, "1");
                continue;
            }

            if (!NumberUtils.isNumber(element))
                tableWithPathList.set(i, "<" + element + ">");

        }

        return tableWithPathList;
    }

}