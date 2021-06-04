package service.user;

import model.Role;
import model.StatusPM;
import model.User;
import service.IService;
import service.connection.ConnectionJDBC;
import service.role.IRoleService;
import service.role.RoleService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService implements IUserService {

    Connection connection = ConnectionJDBC.getConnect();
    IRoleService roleService = new RoleService();

    private static final String SELECT_ALL_USERS = "select * from users";

    @Override
    public ArrayList<User> findAll() throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String birthday = resultSet.getString("birthday");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            String urlOfImage = resultSet.getString("urlOfImage");
            String userName = resultSet.getString("userName");
            String pass = resultSet.getString("pass");
            int role_id = resultSet.getInt("role_id");

            Role role = roleService.getObjectById(role_id);
            users.add(new User(id,name,birthday,email,phone,urlOfImage,userName,pass,role));
        }
        return users;
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
