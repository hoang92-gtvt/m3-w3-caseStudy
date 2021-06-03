package service.category;

import model.Book;
import service.IService;

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
}

