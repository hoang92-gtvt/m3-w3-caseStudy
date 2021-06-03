package service.book;

import model.Book;
import model.Category;
import model.NXB;
import model.StatusBook;
import service.category.CategoryService;
import service.category.ICategoryService;
import service.connection.ConnectionJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBookService {

    public static final String insertBook = "insert into book (name, price) value (?,?)";
    public static final String INSERTINTORELATIVE = "insert into relative(bookID, categoryID) value(?,?)";
    public static final String findBookByID = "select * from book where id=?";
    ICategoryService categoryService = new CategoryService();


    public static final String CALLGETALLBOOK= "call getAllBook()";
    Connection connection = ConnectionJDBC.getConnect();



    @Override
    public Book getBookById(int id) throws SQLException {
        Book oldBook = null;
//        PreparedStatement statement = connection.prepareStatement(findBookByID);
//        statement.setInt(1, id);
//        ResultSet result = statement.executeQuery();
//        while(result.next()){
//            String  name = result.getString("name");
//            String  description = result.getString("description");
//            oldBook = new Book (name, description);
//        }

        return oldBook;
    }

    @Override
    public void edit(int id, Book updateBook, int[] categories_int) throws SQLException {

    }


    @Override
    public ArrayList<Book> findAll() throws SQLException {
        ArrayList<Book> bookList = new ArrayList<>();

        CallableStatement statement =connection.prepareCall(CALLGETALLBOOK);

        ResultSet result = statement.executeQuery();

        while(result.next()){
            int idBook = result.getInt(1);
            String nameBook = result.getString(2);
            String description = result.getString(3);
            String status = result.getString(4);
            String nxb = result.getString(5);


            NXB nxbObject = new NXB(nxb);
            StatusBook statusBook= new StatusBook(status);

            ArrayList<Category> categories = (ArrayList<Category>) categoryService.findCategoryByID(idBook);

            Book book = new Book(idBook,nameBook,description ,nxbObject, statusBook, categories );

            bookList.add(book);

        }

        return bookList;

    }

    @Override
    public void creat(Book newE) {

    }

    @Override
    public void edit(int id, Book book) {

    }

    @Override
    public void delete(int index) {

    }

}
