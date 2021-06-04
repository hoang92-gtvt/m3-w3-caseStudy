package service.user;

import model.Role;
import model.StatusPM;
import model.User;
import service.IService;
import service.connection.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService implements IUserService {

    Connection connection = ConnectionJDBC.getConnect();
    @Override
    public ArrayList<User> findAll() throws SQLException {
        return null;
    }

    @Override
    public void creat(User newE) {

    }

    @Override
    public void edit(int index, User newE) {

    }

    @Override
    public void delete(int index) throws SQLException {

    }

    @Override
    public User getObjectById(int id) throws SQLException {
        User user = null;

        PreparedStatement statement = connection.prepareStatement("select * from users where id =? ");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            int get_id = result.getInt(1);
            String  name = result.getString(2);
            String  birthday = result.getString(3);
            String  email = result.getString(4);
            String  phone = result.getString(5);
            String  urlOfImage = result.getString(6);
            String  userName= result.getString(7);
            String pass= result.getString(8);

            int role_id = result.getInt(9);
            Role role = new Role(role_id);


            user  = new User (get_id, name, birthday,email,phone,urlOfImage,userName,pass,role);
        }

        return user;
    }
}
