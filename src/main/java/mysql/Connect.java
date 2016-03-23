package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Connect {
	protected Connection connection;
	public static final String nameDataBaze = "Opcje";
	protected Statement statement;

	public Connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:" + "src/main/resources/" + nameDataBaze);
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
