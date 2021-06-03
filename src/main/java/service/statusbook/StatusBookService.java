package service.statusbook;

import config.ConnectionJDBC;
import model.Category;
import model.NXB;
import model.StatusBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatusBookService implements  IStatusBookService{
    private Connection connection = ConnectionJDBC.getConnect();
    private String FIND_ALL_STATUSBOOK = "select * from statusbook;";
    private String SAVE_STATUSBOOK= "insert into statusbook(name) value(?);";
    private String DELETE_STATUSBOOK= "delete from statusbook where id = ?;";
    private String UPDATE_STATUSBOOK= "update statusbook set name = ? where id = ?;";

    @Override
    public ArrayList<StatusBook> findAll() {
        ArrayList<StatusBook> Statusbooks= new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_STATUSBOOK);
            ResultSet resultSet = preparedStatement.executeQuery(FIND_ALL_STATUSBOOK);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Statusbooks.add(new StatusBook(id,name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Statusbooks;
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
