package service.category;

import model.Book;
import model.Category;
import service.IService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ICategoryService extends IService<Category> {
    @Override
    ArrayList<Category> findAll() throws SQLException;

    @Override
    void creat(Category newE);

    @Override
    void edit(int index, Category newE);

    @Override
    void delete(int index);
}

