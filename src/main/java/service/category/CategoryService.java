package service.category;

import model.Book;
import model.Category;
import service.connection.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService{

    Connection conection = ConnectionJDBC.getConnect();

    @Override
    public ArrayList<CategoryService> findAll() {
        return null;
    }

    @Override
    public void creat(CategoryService newE) {

    }

    @Override
    public void edit(int index, CategoryService newE) {

    }

    @Override
    public void delete(int index) {

    }

    @Override
    public List<Category> findCategoryByID(int id) throws SQLException {
        List<Category> categories= new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(FINDCATEGORYBYIDOFBOOK);
        statement.setInt(1, idOfBook);

        ResultSet resultSet= statement.executeQuery();

        while(resultSet.next()){
            int categoryId = resultSet.getInt(1);
            String  categoryName = resultSet.getString(2);
            Category category = new Category(categoryId, categoryName);

            categories.add(category);
            System.out.println("");

        }
        System.out.println(categories);
        return categories;
    }
}
