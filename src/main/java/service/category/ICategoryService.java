package service.category;

import model.Book;
import model.Category;
import service.IService;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryService extends IService<CategoryService> {

    public List<Category> findCategoryByID(int id) throws SQLException;
}

