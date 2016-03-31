package pl.bookstore.robot.DAO;


import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by damian on 31.03.16.
 */
public class QueryDAOTest {

    @org.testng.annotations.Test
    public void ifPutUpdateQueryQueryWillExecute(){
        //given
        QueryDAO queryDAO=new QueryDAO();
        Statement statement= mock(Statement.class);
        String query = "Insert into book";

        //when
        queryDAO.updateQuery(statement, query);

        //then
        try {
            verify(statement).executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @org.testng.annotations.Test
    public void ifPutSelectQueryQueryWillExecute(){
        //given
        QueryDAO queryDAO=new QueryDAO();
        Statement statement= mock(Statement.class);
        String query = "Select * from book";

        //when
        queryDAO.updateQuery(statement, query);

        //then
        try {
            verify(statement).executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
