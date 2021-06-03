package service.statusBook;

import model.StatusBook;
import service.connection.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatusBookService implements IStatusBookService{
    public static final String AllSTATUSBOOK = "select sb.name from statusBook as sb;";
    Connection connection = ConnectionJDBC.getConnect();
    @Override
    public ArrayList<StatusBook> findAll() throws SQLException {
        ArrayList<StatusBook> statusBooks = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(AllSTATUSBOOK);

        ResultSet result =statement.executeQuery();

        while(result.next()){
            String statusBook = result.getString(1);
            StatusBook statusBookObject = new StatusBook(statusBook);
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
}
