package pl.bookstore.robot.mysql;

import java.sql.SQLException;
import java.util.LinkedList;

import pl.bookstore.robot.bookstorecontener.*;
public interface OperationMySql {
	LinkedList<BookStore> getListBookStore() throws SQLException;
	int saveListBookStore(String url, String nameSite, boolean isActive) throws SQLException;
	int saveListBookStore(LinkedList<BookStore> bookStory) throws SQLException;
}
