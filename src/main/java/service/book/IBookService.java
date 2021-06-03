package service.book;

import model.Book;
import service.IService;

import java.sql.SQLException;

public interface IBookService extends IService<Book> {


    public void edit(int id, Book updateBook, int[] categories_int) throws SQLException;

    public void create (Book newBook, int[] categoryId) throws SQLException;
}
