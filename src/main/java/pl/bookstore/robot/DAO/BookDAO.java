package pl.bookstore.robot.DAO;

import org.apache.log4j.Logger;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.utils.PojoUtils;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by damian on 30.03.16.
 */
public class BookDAO extends QueryDAO {
    private final String tableName = Book.class.getSimpleName().toLowerCase();
    private Logger logger = Logger.getLogger(BookDAO.class);


    public BookDAO() {
        createTableIfNotExist();
    }

    public void createTableIfNotExist() {
        String createTableQuery = "create table if not exists " + tableName + " (" + PojoUtils.getFieldsWithTypes(Book.class) + ")";
        updateQuery(createTableQuery);
        logger.info("Book Table was created with query " + createTableQuery);
    }

    public void persist(Book book) {
        String insertBookQuery = "INSERT INTO " + tableName + " VALUES (\"" + book.getTitle() + "\", \"" + book.getCategory() + "\", \"a\");";
        updateQuery(insertBookQuery);
        logger.info(book.toString() + "was added to database");
    }


    public List<Book> getBooks() {
        String query = "Select * from " + tableName;

        ResultSet resultSet = selectQuery(query);
        List<Book> books = ResultSetConverter.convertToBook(resultSet);
        logger.info("Retrieved all books from table");

        return books;
    }

}