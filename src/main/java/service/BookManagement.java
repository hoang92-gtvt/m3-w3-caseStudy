package service;

import config.ConnectionJDBC;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManagement {
    private static final String GET_AVAILABLE_BOOK = "select * from book b left join detailpm d on d.id = b.detailpm_id where d.statuspm_id = 3 or b.detailpm_id IS NULL";
    private static final String GET_BOOK_BY_ID = "select * from book where id =? ";
    Connection connection = ConnectionJDBC.getConnect();

    public BookManagement() {
    }

    public List<Book> selectAvailableBooks() throws SQLException{
        List<Book> bookList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_AVAILABLE_BOOK);
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("b.id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            int status_id = resultSet.getInt("status_id");
            int nxb_id = resultSet.getInt("nxb_id");
            bookList.add(new Book(id,name,description,status_id,nxb_id));
        }
        return bookList;
    }
    public List<Book> getBookByTicket(int ticket_id) throws SQLException{
        List<Book> bookList = new ArrayList<>();
        CallableStatement callableStatement = connection.prepareCall("{CALL get_ticket_by_id(?)}");
        callableStatement.setInt(1,ticket_id);
        ResultSet resultSet = callableStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("book_id");
            String name = resultSet.getString("book");
            String description = resultSet.getString("description");
            int status_id = resultSet.getInt("status_id");
            int nxb_id = resultSet.getInt("nxb_id");
            bookList.add(new Book(id,name,description,status_id,nxb_id));
        }
        return bookList;
    }
    public List<Book> getFakeBook(int ticket_id) throws SQLException{
        List<Book> fakebooklist = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from fakebook where ticket_id=?");
        preparedStatement.setInt(1,ticket_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int book_id = resultSet.getInt("book_id");
            Book book = getBookById(book_id);
            fakebooklist.add(book);
        }
        return fakebooklist;
    }
    public Book getBookById(int id) throws SQLException{
        Book book = null;
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOK_BY_ID);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            int status = resultSet.getInt("status_id");
            int nxb_id = resultSet.getInt("nxb_id");
            book = new Book(id,name,description,status,nxb_id);
        }
        return book;
    }
}
