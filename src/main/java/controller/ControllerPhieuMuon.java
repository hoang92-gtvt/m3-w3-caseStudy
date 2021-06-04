package controller;

import model.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ControllerPhieuMuon", value = "/phieumuon")
public class ControllerPhieuMuon extends HttpServlet {
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

    private void showAllPhieuMuon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/phieumuons/list.jsp");

//        ArrayList<Book> bookList = new ArrayList<>();
//
//        bookList = bookService.findAll();
//
//        request.setAttribute("bookList", bookList);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
