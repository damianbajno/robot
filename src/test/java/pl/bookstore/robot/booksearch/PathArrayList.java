package pl.bookstore.robot.booksearch;

import java.util.ArrayList;
import java.util.List;

public class PathArrayList {
    private static ArrayList<String> path;

    private PathArrayList() {
        path=new ArrayList<>();
    }

    static List<String> addElements(String ... pathElements){
        path=new ArrayList<>();
        for (String pathElement : pathElements) {
            path.add(pathElement);
        }
        return path;
    }

}
