package service;

import config.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagement {
    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, birthday, email, phone, userName, pass, role_id) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "select * from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name = ?,birthday= ?, phone=?,urlOfImage=? where id = ?;";
    private static final String CHANGE_PASSWORD = "update users set password =? where id =?; ";
    private static final String SET_ROLE = "update users set role = ? where id =?";
    Connection connection = ConnectionJDBC.getConnect();
    public UserManagement() {
    }

    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getBirthday());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getUserName());
            preparedStatement.setString(6, user.getPass());
            preparedStatement.setInt(7, user.getRole());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }
    public User getUserByUserNameandPass(String username,String pass) throws SQLException{
        User user = null;
        String query = "{CALL get_sign_in(?,?)}";
        CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setString(1,username);
            callableStatement.setString(2,pass);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String birthday = resultSet.getString("birthday");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String urlOfImage = resultSet.getString("urlOfImage");
                String userName = resultSet.getString("userName");
                String Pass = resultSet.getString("pass");
                int role = resultSet.getInt("role_id");
                user = new User(id, name, birthday, email, phone, urlOfImage, userName, Pass, role);
            }
            return user;
    }
    public List<User> selectAllUsers() throws SQLException{
        List<User> users = new ArrayList<>();
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
                String Pass = resultSet.getString("pass");
                int role = resultSet.getInt("role_id");
                users.add(new User(id,name,birthday,email,phone,urlOfImage,userName,Pass,role));
            }
            return users;
    }
    public User selectUser(int id) throws SQLException{
        User user = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String birthday = rs.getString("birthday");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String urlOfImage = rs.getString("urlOfImage");
                String userName = rs.getString("userName");
                String Pass = rs.getString("pass");
                int role = rs.getInt("role_id");
                user = new User(id,name,birthday,email,phone,urlOfImage,userName,Pass,role);
            }
        return user;
    }
    public void setPassword(int id,String newpass) throws SQLException{
        String query = "{CALL update_password(?,?)}";
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1, id);
        callableStatement.setString(2,newpass);
        System.out.println(callableStatement);
        callableStatement.executeUpdate();
    }
    public void updateUser(int id,String name,String birth,String phone, String img) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);
        statement.setString(1, name);
        statement.setString(2, birth);
        statement.setString(3, phone);
        statement.setString(4, img);
        statement.setInt(5,id);
        statement.executeUpdate();
    }




}
