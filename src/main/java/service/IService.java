package service;

import java.util.ArrayList;

public interface IService <E> {
    ArrayList<E> findAll();
    void creat(E newE);
    void edit(int index, E newE);
    void delete(int index);
}
