package service.category;

import model.Book;
import model.Category;
import service.IService;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public interface ICategoryService extends IService<Category> {

    public List<Category> findCategoryByID(int id) throws SQLException;

}


