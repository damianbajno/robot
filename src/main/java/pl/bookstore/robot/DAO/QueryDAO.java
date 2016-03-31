package pl.bookstore.robot.DAO;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by damian on 31.03.16.
 */
public class QueryDAO extends DAO {
    private Logger logger = Logger.getLogger(BookDAO.class);

    void updateQuery(String query) {
        updateQuery(createStatment(), query);
    }

    void updateQuery(Statement statement, String query) {
        try (Statement statment = statement) {
            statment.executeUpdate(query);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    ResultSet selectQuery(String query) {
        return selectQuery(createStatment(), query);
    }

    ResultSet selectQuery(Statement statement, String query) {
        ResultSet resultSet = null;
        try (Statement statment = statement) {
            resultSet = statment.executeQuery(query);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return resultSet;
    }
}
