package service.category;

import model.Book;
import model.Category;
import service.connection.ConnectionJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService{

    public static final String CALL_GET_CATEGORY_BY_ID_BOOK = "call getCategoryByIdBook(?)";
    Connection connection = ConnectionJDBC.getConnect();

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
    public List<Category> findCategoryByID(int idOfBook) throws SQLException {
        List<Category> categories= new ArrayList<>();

        CallableStatement statement = connection.prepareCall(CALL_GET_CATEGORY_BY_ID_BOOK);
        statement.setInt(1, idOfBook);

        ResultSet resultSet= statement.executeQuery();

        while(resultSet.next()){

            String  categoryName = resultSet.getString(1);
            Category category = new Category(categoryName);

            categories.add(category);
            System.out.println("");

        }
        System.out.println(categories);
        return categories;
    }
}
