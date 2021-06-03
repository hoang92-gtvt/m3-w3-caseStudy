package service.nxb;

import config.ConnectionJDBC;
import model.NXB;
import service.IService;

import java.sql.Connection;
import java.util.ArrayList;

public interface INXBService<N> extends IService<NXB> {

    @Override
    ArrayList<NXB> findAll();

    @Override
    void creat(NXB newE);

    @Override
    void edit(int index, NXB newE);

    @Override
    void delete(int index);
}
