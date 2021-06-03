package service.category;

import config.ConnectionJDBC;
import model.Book;
import model.Category;

import java.sql.*;
import java.util.ArrayList;

public class CategoryService implements ICategoryService {
    private Connection connection = ConnectionJDBC.getConnect();
    private String FIND_ALL_CATEGORY = "select * from category;";
    private String SAVE_CATEGORY = "insert into category(name) value(?);";
    private String DELETE_CATEGORY= "delete from category where id = ?;";
    private String UPDATE_CATEGORY = "update category set name = ? where id = ?;";
    @Override
    public ArrayList<Category> findAll() throws SQLException {
        ArrayList<Category> categories = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(FIND_ALL_CATEGORY);
        ResultSet result = statement.executeQuery();
        while (result.next()){
            int id = result.getInt(1);
            String name = result.getString(2);
            Category category= new Category(id, name);
            categories.add(category);
        }

        return categories;
    }

    @Override
    public void creat(Category newE) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CATEGORY);
            preparedStatement.setString(1,newE.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void edit(int index, Category newE) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY);
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY);
            preparedStatement.setInt(1,index+1);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    }

