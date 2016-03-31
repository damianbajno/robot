package pl.bookstore.robot.DAO;

import org.apache.log4j.Logger;
import pl.bookstore.robot.pojo.Book;
import pl.bookstore.robot.utils.PojoUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by damian on 30.03.16.
 */
public class BookDAO {
    private final String tableName = Book.class.getSimpleName().toLowerCase();

    private Logger logger = Logger.getLogger(BookDAO.class);
    private DAO dao;

    public BookDAO() {
        dao = new DAO();
    }

    public void createTableIfNotExist() {
        String createTableQuery = "create table if not exists " + tableName + " (" + PojoUtils.getFieldsWithTypes(Book.class) + ")";
        updateQuery(dao.createStatment(), createTableQuery);
        logger.info("Book Table was created");
    }

    public void persist(Book book) {
        String insertBookQuery = "INSERT INTO " + tableName + " VALUES (" + book.getTitle() + ", " + book.getCategory() + ");";
        updateQuery(dao.createStatment(), insertBookQuery);
        logger.info("Book table was added to database");
    }

    protected void updateQuery(Statement statement, String query) {
        try (Statement statment = statement) {
            statment.executeUpdate(query);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    public List<Book> getBooks() {
        final String query = "Select * from " + tableName;

        ResultSet resultSet = selectQuery(dao.createStatment(),query);
        List<Book> books = ResultSetConverter.convertToBook(resultSet);
        logger.info("Book table was added to database");

        return books;
    }

    public ResultSet selectQuery(Statement statement, String query) {
        ResultSet resultSet = null;
        try (Statement statment = dao.createStatment()) {
            resultSet = statment.executeQuery("Select * from " + tableName);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return resultSet;
    }

}
