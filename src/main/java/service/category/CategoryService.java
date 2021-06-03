package service.category;

import model.Book;
<<<<<<< HEAD
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
=======

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryService implements ICategoryService {
    private static final  String jdbcURL = "";
    private static final  String jdbcUserName = "";
    private static final  String jdbcPassword = "";
    protected  static  Connection getConnection(){
        Connection connection  = null;
        try {
            Class.forName("com.mysql.cf.jdbc.Drive");
            connection = DriverManager.getConnection(jdbcURL,jdbcUserName,jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public ArrayList<Book> findAll() {
>>>>>>> origin/toan
        return null;
    }

    @Override
<<<<<<< HEAD
    public void creat(CategoryService newE) {
=======
    public void creat(Book newE) {
>>>>>>> origin/toan

    }

    @Override
<<<<<<< HEAD
    public void edit(int index, CategoryService newE) {
=======
    public void edit(int index, Book newE) {
>>>>>>> origin/toan

    }

    @Override
    public void delete(int index) {

    }
<<<<<<< HEAD

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
=======
>>>>>>> origin/toan
}
