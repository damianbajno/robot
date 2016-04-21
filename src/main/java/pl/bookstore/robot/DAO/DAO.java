package pl.bookstore.robot.DAO;

import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by damian on 30.03.16.
 */
public class DAO {
    private static final String DATABASE_NAME = "OpcjaDataBase";

    private static Logger logger = Logger.getLogger(DAO.class);
    private static Connection connection;

    static {
        createConnection();
    }

    protected DAO() {

    }

    private static void createConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
        } catch (ClassNotFoundException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

     Statement createStatment() {
        Statement statement=null;
        try {
            statement=connection.createStatement();
        } catch (SQLException e){
            logger.error(e);
        }
        return statement;
    }

    PreparedStatement createPreparedStatmentToRemoveBookStoreByName() {
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement("delete from bookstore where name=?");
        } catch (SQLException e){
            logger.error(e.getMessage());
        }
        return preparedStatement;
    }

    void closeConnections(){
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

}
