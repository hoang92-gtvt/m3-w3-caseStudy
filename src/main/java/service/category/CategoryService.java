package service.category;

import model.Book;

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
        return null;
    }

    @Override
    public void creat(Book newE) {

    }

    @Override
    public void edit(int index, Book newE) {

    }

    @Override
    public void delete(int index) {

    }
}
