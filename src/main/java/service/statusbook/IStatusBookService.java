package service.statusbook;

import model.StatusBook;
import service.IService;

import java.util.ArrayList;

public interface IStatusBookService extends IService<StatusBook> {
    @Override
    ArrayList<StatusBook> findAll();

    @Override
    void creat(StatusBook newE);

    @Override
    void edit(int index, StatusBook newE);

    @Override
    void delete(int index);
}
