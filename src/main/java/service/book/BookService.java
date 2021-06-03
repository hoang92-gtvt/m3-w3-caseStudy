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


    public static final String INSERTINTORELATIVE = "insert into relative(bookID, categoryID) value(?,?)";
    public static final String findBookByID = "select * from book where id=?";
    public static final String insertBook = "insert into book(name, description, status_id, nxb_id, urlOfImage) value (?,?,?,?,?)";
    public static final String insertBooK_Category = "insert into book_category(book_id, category_id) value(?,?)";

    ICategoryService categoryService = new CategoryService();


    public static final String CALLGETALLBOOK= "call getAllBook()";
    Connection connection = ConnectionJDBC.getConnect();



    @Override
    public Book getObjectById (int id) throws SQLException {
        Book oldBook = null;
        PreparedStatement statement = connection.prepareStatement(findBookByID);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        while(result.next()){

            String  name = result.getString("name");
            String  description = result.getString("description");
            String  urlOfImage = result.getString("urlOfImage");

            oldBook = new Book (name, description, urlOfImage);
        }

        return oldBook;
    }

    @Override
    public void edit(int id, Book updateBook, int[] categories_int) throws SQLException {

    }

    @Override
    public void create(Book newBook, int[] categoryId) throws SQLException {


     connection.setAutoCommit(false);

     PreparedStatement statement = connection.prepareStatement(insertBook, PreparedStatement.RETURN_GENERATED_KEYS );
     statement.setString(1, newBook.getName());
     statement.setString(2, newBook.getDescription());
     statement.setInt(3, newBook.getStatusBook().getId());
     statement.setInt(4, newBook.getNxb().getId());
     statement.setString(5, newBook.getUrlOfImage());

     statement.executeUpdate();
     int id=0;
     ResultSet result  = statement.getGeneratedKeys();
        while(result.next()){
            id = result.getInt(1);
        }

     PreparedStatement statement1 = connection.prepareStatement(insertBooK_Category);

        for (int i = 0; i < categoryId.length; i++) {
            statement1.setInt(1, id);
            statement1.setInt(2, categoryId[i]);
            statement1.executeUpdate();
        }

     connection.commit();


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
