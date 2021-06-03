package service.book;

import model.Book;
import service.IService;

import java.sql.SQLException;

public interface IBookService extends IService<Book> {



    public  Book getBookById(int id) throws SQLException;

    public void edit(int id, Book updateBook, int[] categories_int) throws SQLException;

    public void create (Book newBook, int[] categoryId);
}
