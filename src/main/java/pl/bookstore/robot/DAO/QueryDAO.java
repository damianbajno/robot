package pl.bookstore.robot.DAO;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by damian on 31.03.16.
 */
public class QueryDAO extends DAO {
    private Logger logger = Logger.getLogger(BookDAO.class);


    void updateParametrizedQuery(String bookStoreName) {
        updateParametrizedQuery(createPreparedStatmentToRemoveBookStoreByName(), bookStoreName);
    }

    void updateParametrizedQuery(PreparedStatement preparedStatement, String bookStoreName) {
        try  {
            preparedStatement.setString(1,bookStoreName);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error(e.getMessage()+"  "+bookStoreName);
        }
    }
    
    void updateQuery(String query) {
        updateQuery(createStatment(), query);
    }

    void updateQuery(Statement statement, String query) {
        try  {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error(e.getMessage()+query);
        }
    }

    ResultSet selectQuery(String query) {
        return selectQuery(createStatment(), query);
    }

    ResultSet selectQuery(Statement statement, String query) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return resultSet;
    }
}
