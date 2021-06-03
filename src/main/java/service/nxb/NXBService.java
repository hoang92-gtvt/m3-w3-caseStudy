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
    private String FIND_ALL_nxb = "select * from nxb;";
    private String SAVE_nxb = "insert into nxb(name) value(?);";
    private String DELETE_nxb= "delete from nxb where id = ?;";
    private String UPDATE_nxb = "update nxb set name = ? where id = ?;";
    @Override
    public ArrayList<NXB> findAll() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CATEGORY);
            ResultSet resultSet = preparedStatement.executeQuery(FIND_ALL_CATEGORY);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                categories.add(new Category(id,name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
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
}
