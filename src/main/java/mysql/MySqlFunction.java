package mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import bookstorecontener.BookStore;

public class MySqlFunction extends Connect implements OperationMySql {
	private ResultSet rs;

	public MySqlFunction() throws ClassNotFoundException, SQLException {
		super();
	}

	@Override
	public int saveListBookStore(String url, String nameSite, boolean isActive) throws SQLException {
		int isActiveInt = 0;
		if (isActive)
			isActiveInt = 1;

		int result = statement
				.executeUpdate("insert into booksstores values('" + url + "','" + nameSite + "'," + isActiveInt + ")");
		return result;
	}

	@Override
	public LinkedList<BookStore> getListBookStore() throws SQLException {
		LinkedList<BookStore> bookStore = new LinkedList<>();
		rs = statement.executeQuery("select * from booksstores");
		while (rs.next()) {
			boolean isActove = false;
			if (rs.getInt("isactive") == 1)
				isActove = true;
			bookStore.add(new BookStore(rs.getString("url"), rs.getString("sitename"), isActove));
		}
		return bookStore;
	}

	@Override
	public void close() {
		try {
			super.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Connect dropTableToNewSave() throws SQLException {
		statement.executeUpdate("drop table if exists booksstores");
		creteTable();
		return this;
	}

}