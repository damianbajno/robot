package pl.bookstore.robot.booksearch;

import java.util.List;

/**
 * Created by damian on 5/12/16.
 */
class Steps {
    List<String> stepList;
    int countIdx = 0; // cause first count matters not, we ignore it
    int itemIdx = -1;

    Steps(List<String> path) {
        this.stepList = path;
    }

    public static Steps from(List<String> path) {
        return new Steps(path);
    }

    public String nextItem() {
        itemIdx += 2;
        return stepList.get(itemIdx);
    }

    public int nextCount() {
        countIdx += 2;
        return Integer.parseInt(stepList.get(countIdx))-1;
    }

    public boolean hasNext() {
        int size = stepList.size();
        return size > itemIdx+2 || size > countIdx+2;
    }
}
