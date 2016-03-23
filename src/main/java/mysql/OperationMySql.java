package mysql;

import java.sql.SQLException;
import java.util.LinkedList;
import bookstorecontener.*;
public interface OperationMySql {
	LinkedList<BookStore> getListBookStore() throws SQLException;
	int saveListBookStore(String url, String nameSite, boolean isActive) throws SQLException;
}
