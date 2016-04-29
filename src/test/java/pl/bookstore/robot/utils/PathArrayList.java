package pl.bookstore.robot.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damian on 4/28/16.
 */
public class PathArrayList {
    ArrayList<String> path;

    public PathArrayList() {
        path=new ArrayList<>();
    }

    public List<String> addElements(String ... pathElements){
        path=new ArrayList<>();
        for (String pathElement : pathElements) {
            path.add(pathElement);
        }
        return path;
    }

    public int size() {
        return path.size();
    }
}
