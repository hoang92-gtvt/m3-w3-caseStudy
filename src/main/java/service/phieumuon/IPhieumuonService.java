package service.phieumuon;

import model.PhieuMuon;
import service.IService;

import java.sql.SQLException;

public interface IPhieumuonService extends IService<PhieuMuon> {

    public void create(PhieuMuon phieumuon, int[] book_id) throws SQLException;
}
