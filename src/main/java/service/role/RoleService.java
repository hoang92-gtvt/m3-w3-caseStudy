package service.role;

import model.Role;
import service.connection.ConnectionJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleService implements IRoleService{
    Connection connection = ConnectionJDBC.getConnect();
    private static final String SELECT_ROLE_BY_ID = "select * from role where id =?";

    @Override
    public ArrayList<Role> findAll() throws SQLException {
        return null;
    }

    @Override
    public void creat(Role newE) {

    }

    @Override
    public void edit(int index, Role newE) {

    }

    @Override
    public void delete(int index) throws SQLException {

    }

    @Override
    public Role getObjectById(int id) throws SQLException {
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
