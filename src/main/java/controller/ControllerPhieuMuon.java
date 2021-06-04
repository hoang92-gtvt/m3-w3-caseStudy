package controller;

import model.Book;
import model.PhieuMuon;
import service.phieumuon.IPhieumuonService;
import service.phieumuon.PhieuMuonService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ControllerPhieuMuon", value = "/phieumuon")
public class ControllerPhieuMuon extends HttpServlet {

    IPhieumuonService phieumuonService = new PhieuMuonService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        try {
            if (action == null) {
                action = "";
            }
            switch (action) {

//                case "create":
//                    showFormCreate(request, response);
//                    break;
//
//                case "edit":
//                    showFormEdit(request, response);
//                    break;
//
//                case "delete":
//                    showFormDelete(request,response);
                default:
                    showAllPhieuMuon(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAllPhieuMuon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        ArrayList<PhieuMuon> pmList = new ArrayList<>();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/phieumuons/list.jsp");

        pmList = phieumuonService.findAll();

        request.setAttribute("pmList", pmList);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
