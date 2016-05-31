package pl.bookstore.robot.pojo;

/**
 * Created by damian on 5/11/16.
 *
 * Exception is thrown when user don't select no category.
 */
public class NoCategorySelectedException extends Exception{

    public NoCategorySelectedException(String message) {
        super(message);
    }
}
