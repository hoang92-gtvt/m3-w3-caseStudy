package service.nxb;

import model.NXB;
import model.StatusBook;
import service.connection.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NXBService implements INXBService{
    public static final String SELECT_ALL_NXB = "select * from nxb;";
    Connection connection = ConnectionJDBC.getConnect();

    @Override
    public ArrayList<NXB> findAll() throws SQLException {
        ArrayList<NXB> NXBList= new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_NXB);
        ResultSet result =statement.executeQuery();

        while(result.next()){
            int id = result.getInt(1);
            String nxb = result.getString(2);
            NXB nxbObject = new NXB(id, nxb);
            NXBList.add(nxbObject);
        }
        return NXBList;
    }

    @Override
    public void creat(NXB newE) {

    }

    @Override
    public void edit(int index, NXB newE) {

    }

    @Override
    public void delete(int index) {

    }

    @Override
    public NXB getObjectById(int id) throws SQLException {
        return null;
    }
}
