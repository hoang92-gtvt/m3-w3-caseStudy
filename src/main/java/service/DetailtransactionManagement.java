package service;

import config.ConnectionJDBC;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailtransactionManagement {
    private static final String INSERT_TICKET = "insert into detailpm (PM_id,borrowdate,duedate,statuspm_id)values (?,?,?,?)";
    private static final String SET_BOOK_TICKET = "UPDATE book SET detailpm_id = ? WHERE id = ?";
    private static final String GET_TICKET_BY_CUSTOMER_ID = "select * from detailpm where PM_id=?";
    private static final String SET_STATUS_TICKET = "UPDATE detailpm SET statuspm_id=? where id=?;";
    private static final String UPDATE_BOOK_TICKET = "update book set detailpm_id = null where detailpm_id = ?";
    private static final String DELET_TICKET_BY_CUSTOMER_ID = "delete from detailpm where PM_id=?;";
    private static final String DELETE_CUSTOMER = "DELETE FROM phieumuon WHERE id =?";
    private static final String GET_TICKET_IN4_BY_ID = "select * from detailpm where id = ?";
    BookManagement bookManagement = new BookManagement();
    CustomerManagement customerManagement = new CustomerManagement();
    TransactionStateManagement transactionStateManagement = new TransactionStateManagement();
    Connection connection = ConnectionJDBC.getConnect();

    public DetailtransactionManagement() {
    }
    public void createBorrowTicket(Detailtransaction detailtransaction, int[] Books) throws SQLException {
        int id=0;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_TICKET, Statement.RETURN_GENERATED_KEYS);
            preparedStatement1.setInt(1, detailtransaction.getCustomer().getId());
            preparedStatement1.setString(2, detailtransaction.getBorrowdate());
            preparedStatement1.setString(3, detailtransaction.getDuedate());
            preparedStatement1.setInt(4, detailtransaction.getTransactionStatus().getId());
            int temp = preparedStatement1.executeUpdate();
            ResultSet resultSet = preparedStatement1.getGeneratedKeys();
            if (resultSet.next()) {
                System.out.println(resultSet);
                id = resultSet.getInt(1);
            }
            if (temp==1) {
                PreparedStatement preparedStatement = connection.prepareStatement(SET_BOOK_TICKET);
                for (int book_id: Books) {
                    preparedStatement.setInt(1,id);
                    preparedStatement.setInt(2,book_id);
                    preparedStatement.executeUpdate();
                }
                List<Book> fakebooklist = bookManagement.getBookByTicket(id);
                PreparedStatement preparedStatement2 = connection.prepareStatement("insert into fakebook(ticket_id,book_id) VALUES (?,?)");
                for (Book book: fakebooklist) {
                    preparedStatement2.setInt(1,id);
                    preparedStatement2.setInt(2,book.getId());
                    preparedStatement2.executeUpdate();
                }
                connection.commit();
            }
        }catch (SQLException throwables) {
            connection.rollback();
        }
    }
    public List<Detailtransaction> getTicketsByCustomer(int id) throws SQLException{
        List<Detailtransaction> detailtransactions = new ArrayList<>();
        CallableStatement callableStatement = connection.prepareCall("{CALL get_all_tickets_by_customer(?)}");
        callableStatement.setInt(1, id);
        System.out.println(callableStatement);
        ResultSet resultSet = callableStatement.executeQuery();
        while (resultSet.next()) {
            int ticket_id = resultSet.getInt("id");
            String brrwdate = resultSet.getString("borrowdate");
            String duedate = resultSet.getString("duedate");
            int status_id = resultSet.getInt("statuspm_id");
            TransactionStatus transactionStatus = transactionStateManagement.findStatusById(status_id);
            List<Book> bookList = bookManagement.getBookByTicket(ticket_id);
            Customer customer = customerManagement.selectCustomer(id);
            detailtransactions.add(new Detailtransaction(ticket_id,customer,brrwdate,duedate,transactionStatus,bookList));
        }
        return detailtransactions;
    }
    public void updateTicket(int status_id,int ticket_id) throws SQLException {
            PreparedStatement statement = connection.prepareStatement(SET_STATUS_TICKET);
            statement.setInt(1, status_id);
            statement.setInt(2, ticket_id);
            statement.executeUpdate();
    }
    public void deleteCustomer(int customer_id) throws SQLException{
        List<Detailtransaction> detailtransactionList = getTicketsByCustomer(customer_id);
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement1 = connection.prepareStatement(UPDATE_BOOK_TICKET);
            for (Detailtransaction detailtransaction: detailtransactionList) {
                preparedStatement1.setInt(1,detailtransaction.getId());
                preparedStatement1.executeUpdate();
            }
            PreparedStatement preparedStatement2 = connection.prepareStatement(DELET_TICKET_BY_CUSTOMER_ID);
            preparedStatement2.setInt(1,customer_id);
            preparedStatement2.executeUpdate();
            PreparedStatement preparedStatement3 = connection.prepareStatement(DELETE_CUSTOMER);
            preparedStatement3.setInt(1,customer_id);
            preparedStatement3.executeUpdate();
            connection.commit();
        }catch (SQLException throwables) {
            connection.rollback();
        }
    }
    public Detailtransaction getTicketById(int id) throws SQLException{
        Detailtransaction detailtransaction = null;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_TICKET_IN4_BY_ID);
        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int customer_id = resultSet.getInt("PM_id");
            String brrwdate = resultSet.getString("borrowdate");
            String duedate = resultSet.getString("duedate");
            int status_id = resultSet.getInt("statuspm_id");
            TransactionStatus transactionStatus = transactionStateManagement.findStatusById(status_id);
            List<Book> bookList = bookManagement.getBookByTicket(id);
            Customer customer = customerManagement.selectCustomer(customer_id);
            detailtransaction = new Detailtransaction(id,customer,brrwdate,duedate,transactionStatus,bookList);
        }
        return detailtransaction;
    }
}
