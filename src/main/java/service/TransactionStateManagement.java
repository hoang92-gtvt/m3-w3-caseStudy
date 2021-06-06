package service;

import config.ConnectionJDBC;
import model.TransactionStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionStateManagement {
    public static final String SELECT_ALL_STATUS = "select * from statuspm";
    public static final String SELECT_STATUS_BY_ID = "select * from statuspm where id=?";
    public static final String SELECT_STATUS_BY_NAME = "select * from statuspm where name=?";
    Connection connection = ConnectionJDBC.getConnect();

    public TransactionStateManagement() {
    }
    public List<TransactionStatus> findAll() throws SQLException {
        List<TransactionStatus> transactionStatuses = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_STATUS);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            TransactionStatus transactionStatus = new TransactionStatus(id,name);
            transactionStatuses.add(transactionStatus);
        }
        return transactionStatuses;
    }
    public TransactionStatus findStatusById(int id) throws SQLException{
        TransactionStatus transactionStatus = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATUS_BY_ID);
        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            transactionStatus = new TransactionStatus(id,name);
        }
        return transactionStatus;
    }
    public TransactionStatus findStatusByName(String name) throws SQLException{
        TransactionStatus transactionStatus = null;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATUS_BY_NAME);
        preparedStatement.setString(1, name);
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int status_id = resultSet.getInt("id");
            transactionStatus = new TransactionStatus(status_id,name);
        }
        return transactionStatus;
    }

}
