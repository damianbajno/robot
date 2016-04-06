package pl.bookstore.robot.DAO;

import org.apache.log4j.Logger;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.pojo.BookStore;
import pl.bookstore.robot.utils.PojoUtils;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by damian on 30.03.16.
 */
public class BookStoreDAO extends QueryDAO {
    private final String tableName = Book.class.getSimpleName().toLowerCase();
    private Logger logger = Logger.getLogger(BookStoreDAO.class);

    public BookStoreDAO() {
    }

    public void createTableIfNotExist() {
        String createTableQuery = "create table if not exists " + tableName + " (" + PojoUtils.getFieldsWithTypes(BookStore.class) + ")";
        updateQuery(createTableQuery);
        logger.info("BookStore Table was created");
    }

    public void persist(BookStore bookStore) {
        String insertBookQuery = "INSERT INTO " + tableName + " VALUES (" + bookStore.getName() + ", " + bookStore.getUrl() + ");";
        updateQuery(insertBookQuery);
        logger.info("BookStore table was added to database");
    }


    public List<BookStore> getBooks() {
        String query = "Select * from " + tableName;

        ResultSet resultSet = selectQuery(query);
        List<BookStore> books = ResultSetConverter.convertToBookStore(resultSet);
        logger.info("BookStore table was added to database");

        return books;
    }
}
