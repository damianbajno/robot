package pl.bookstore.robot.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import pl.bookstore.robot.Main;

public abstract class Connect {
	final static Logger logger = Logger.getLogger(Main.class);
	protected Connection connection;
	public static final String nameDataBaze = "OpcjeDataBase";
	protected Statement statement;

	public Connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:" + nameDataBaze);
		statement = connection.createStatement();
		creteTable();
	}

	protected Connect creteTable() throws SQLException {
		statement
				.executeUpdate("create table if not exists booksstores (url string, sitename string, isactive string)");
		return this;
	}

	protected abstract Connect dropTableToNewSave() throws SQLException;

	public abstract void close();
 
}
