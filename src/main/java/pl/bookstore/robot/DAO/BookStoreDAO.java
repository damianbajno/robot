package pl.bookstore.robot.DAO;

import org.apache.log4j.Logger;
import pl.bookstore.robot.pojo.BookStore;
import pl.bookstore.robot.utils.PojoUtils;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by damian on 30.03.16.
 */
public class BookStoreDAO extends QueryDAO {
    private final String tableName = BookStore.class.getSimpleName().toLowerCase();
    private Logger logger = Logger.getLogger(BookStoreDAO.class);

    public BookStoreDAO() {
        createTableIfNotExist();
    }

    public void createTableIfNotExist() {
        String createTableQuery = "create table if not exists " + tableName + " (" + PojoUtils.getFieldsWithTypes(BookStore.class) + ")";
        updateQuery(createTableQuery);
        logger.info("BookStore Table was created with query: " + createTableQuery);
    }

    public void persist(BookStore bookStore) {
        String insertBookQuery = "INSERT INTO " + tableName + " VALUES (\"" + bookStore.getName() + "\", \"" + bookStore.getUrl() + "\", \"" + bookStore.getSearchForBook() + "\", \"" + bookStore.getSearchForTitle() + "\", \"" + bookStore.getSearchForCategory() + "\");";
        logger.info("Inserted query = "+insertBookQuery);
        updateQuery(insertBookQuery);
        logger.info(bookStore.toString() + " was added to table " + tableName);
    }


    public List<BookStore> getBookStores() {
        String query = "Select * from " + tableName;
        logger.info("BookStores query = "+query);
        ResultSet resultSet = selectQuery(query);
        List<BookStore> books = ResultSetConverter.convertToBookStore(resultSet);
        logger.info("BookStores selected from database");
        return books;
    }

    public void remove(BookStore bookStore) {
        updateParametrizedQuery(bookStore.getName());
        logger.info("Removed from table = "+bookStore.toString());
    }
}
