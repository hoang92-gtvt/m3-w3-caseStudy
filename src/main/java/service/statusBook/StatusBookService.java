package service.statusBook;

import model.StatusBook;
import service.connection.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatusBookService implements IStatusBookService{
    public static final String AllSTATUSBOOK = "select * from statusBook as sb;";
    Connection connection = ConnectionJDBC.getConnect();
    @Override
    public ArrayList<StatusBook> findAll() throws SQLException {
        ArrayList<StatusBook> statusBooks = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(AllSTATUSBOOK);

        ResultSet result =statement.executeQuery();

        while(result.next()){
            int id = result.getInt(1);
            String statusBook = result.getString(2);
            StatusBook statusBookObject = new StatusBook(id,statusBook);
            statusBooks.add(statusBookObject);
        }

        return statusBooks;
    }

    @Override
    public void creat(StatusBook newE) {

    }

    @Override
    public void edit(int index, StatusBook newE) {

    }

    @Override
    public void delete(int index) {

    }

    @Override
    public StatusBook getObjectById(int id)throws SQLException {
        return null;
    }
}
