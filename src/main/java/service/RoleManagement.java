package service;

import config.ConnectionJDBC;
import model.Role;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleManagement {
    private static final String SELECT_ALL_ROLES = "select * from role";
    private static final String SELECT_ROLE_BY_ID = "select * from role where id =?";
    Connection connection = ConnectionJDBC.getConnect();
    public RoleManagement() {
    }
    public List<Role> findAll() throws SQLException {
        List<Role> roleList = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ROLES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Role role = new Role(id, name);
                roleList.add(role);
            }
        return roleList;
    }
    public Role findRoleById(int id) throws SQLException{
        Role role = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_ID);
        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int roleid = resultSet.getInt("id");
            String name = resultSet.getString("name");
            role = new Role(roleid,name);
        }
        return role;
    }
}
