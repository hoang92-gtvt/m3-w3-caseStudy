package service.statusPM;

import model.Book;
import model.StatusBook;
import model.StatusPM;
import service.connection.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatusPMService implements IStatusPMService{

    Connection connection = ConnectionJDBC.getConnect();

    @Override
    public ArrayList<StatusPM> findAll() throws SQLException {
        ArrayList<StatusPM> statusPMList = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement("select * from statuspm ;");

        ResultSet result =statement.executeQuery();

        while(result.next()){
            int id = result.getInt(1);
            String statusPM = result.getString(2);
            StatusPM statusPMObject = new StatusPM(id,statusPM);
            statusPMList.add(statusPMObject);
        }

        return statusPMList;
    }

    @Override
    public void creat(StatusPM newE) {

    }

    @Override
    public void edit(int index, StatusPM newE) {

    }

    @Override
    public void delete(int index) throws SQLException {

    }

    @Override
    public StatusPM getObjectById(int id) throws SQLException {
        StatusPM statusPM = null;

        PreparedStatement statement = connection.prepareStatement("select * from statusPM where id =? ");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            int get_id = result.getInt(1);
            String  name = result.getString(2);

            statusPM  = new StatusPM (get_id, name);
        }

        return statusPM ;
    }
}
