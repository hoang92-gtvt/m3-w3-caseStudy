package service.category;

import model.Book;
import model.Category;
import service.IService;

<<<<<<< HEAD
import java.sql.SQLException;
import java.util.List;

public interface ICategoryService extends IService<CategoryService> {

    public List<Category> findCategoryByID(int id) throws SQLException;
=======
import java.util.ArrayList;

public interface ICategoryService extends IService<Book> {
    @Override
    ArrayList<Book> findAll();

    @Override
    void creat(Book newE);

    @Override
    void edit(int index, Book newE);

    @Override
    void delete(int index);
>>>>>>> origin/toan
}

