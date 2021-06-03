package service.nxb;

import config.ConnectionJDBC;
import model.Category;
import model.NXB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NXBService implements  INXBService<NXB>{
    private Connection connection = ConnectionJDBC.getConnect();
    private String FIND_ALL_NXB = "select * from nxb;";
    private String SAVE_NXB = "insert into nxb(name) value(?);";
    private String DELETE_NXB= "delete from nxb where id = ?;";
    private String UPDATE_NXB = "update nxb set name = ? where id = ?;";
    @Override
    public ArrayList<NXB> findAll() {
        ArrayList<NXB> nhaxuatban = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_NXB);
            ResultSet resultSet = preparedStatement.executeQuery(FIND_ALL_NXB);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
               nhaxuatban.add(new NXB(id,name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nhaxuatban;
    }

    @Override
    public void creat(NXB newE) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NXB);
            preparedStatement.setString(1,newE.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void edit(int index, NXB newE) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NXB);
            preparedStatement.setString(1,newE.getName());
            preparedStatement.setInt(2,index+1);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int index) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NXB);
            preparedStatement.setInt(1,index+1);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
