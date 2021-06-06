package service;

import config.ConnectionJDBC;
import model.Customer;
import model.Detailtransaction;
import model.Role;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerManagement {
    private static final String INSERT_CUSTOMER = "INSERT INTO phieumuon" + "  (identity,user_id) VALUES " +
            " (?,?)";
    private static final String INSERT_USERS_SQL= "INSERT INTO users"+"(name, birthday, email, phone, urlOfImage, role_id) VALUES"+" ( ?,?,?,?,?,3)";
    private static final String SELECT_CUSTOMER_BY_ID = "select * from phieumuon where id =?";
    private static final String SELECT_ALL_CUSTOMER = "select * from phieumuon";
    private static final String SELECT_CUSTOMER_BY_IDENTITY = "select * from phieumuon where identity=?";


    UserManagement userManagement = new UserManagement();

    Connection connection = ConnectionJDBC.getConnect();

    public CustomerManagement(){
    }

    public void insertCustomer(User user,String identtity) throws SQLException{
        int id=0;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_USERS_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement1.setString(1, user.getName());
            preparedStatement1.setString(2, user.getBirthday());
            preparedStatement1.setString(3, user.getEmail());
            preparedStatement1.setString(4, user.getPhone());
            preparedStatement1.setString(5, user.getUrlOfImg());
            int temp = preparedStatement1.executeUpdate();
            ResultSet resultSet = preparedStatement1.getGeneratedKeys();
            if (resultSet.next()) {
                System.out.println(resultSet);
                id = resultSet.getInt(1);
            }
            if (temp==1) {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER);
                preparedStatement.setString(1, identtity);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            }
            connection.commit();
        }catch (SQLException throwables) {
            connection.rollback();
        }
    }
    public List<Customer> selectAllCustomers() throws SQLException{
        List<Customer> customerList = new ArrayList<>();
        CallableStatement callableStatement = connection.prepareCall("{CALL get_customer()}");
        System.out.println(callableStatement);
        ResultSet resultSet = callableStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String identity = resultSet.getString("identity");
            int user_id = resultSet.getInt("user_id");
            String name = resultSet.getString("name");
            String birthday = resultSet.getString("birthday");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            String urlOfImage = resultSet.getString("urlOfImage");
            User user = new User(user_id,name,birthday,email,phone,urlOfImage);
            Customer customer = new Customer(id,identity,user);
            customerList.add(customer);
        }
        return customerList;
    }
    public Customer selectCustomer(int id) throws SQLException{
        Customer customer = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String identity = resultSet.getString("identity");
            int user_id = resultSet.getInt("user_id");
            User user = userManagement.selectUser(user_id);
            customer = new Customer(id,identity,user);
        }
        return customer;
    }
    public void setCustomer(User user,String identity)throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER);
        preparedStatement.setString(1, identity);
        preparedStatement.setInt(2, user.getId());
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
    }
    public List<Customer> selectbyIdentity(String search)throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        CallableStatement callableStatement = connection.prepareCall("{CALL get_customer_by_identity(?)}");
        callableStatement.setString(1,search);
        ResultSet resultSet = callableStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String identity = resultSet.getString("identity");
            int user_id = resultSet.getInt("user_id");
            String name = resultSet.getString("name");
            String birthday = resultSet.getString("birthday");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            String urlOfImage = resultSet.getString("urlOfImage");
            User user = new User(user_id,name,birthday,email,phone,urlOfImage);
            Customer customer = new Customer(id,identity,user);
            customerList.add(customer);
        }
        return customerList;
    }


}


