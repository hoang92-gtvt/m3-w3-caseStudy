package controller;

import model.Book;
import service.book.BookService;
import service.book.IBookService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ControllerBook", value ="/book")
public class ControllerBook extends HttpServlet {
    IBookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        try {
            if (action == null) {
                action = "";
            }
            switch (action) {

//                case "":
//                    showAllBook(request,response);
//                    break;
//                case "creat":
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
                    showAllBook(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showAllBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/books/list.jsp");

        ArrayList<Book> bookList = new ArrayList<>();

        bookList = bookService.findAll();

        request.setAttribute("bookList", bookList);

        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                action = "";
            }
            switch (action) {
//                case "creat":
//                    Create(request, response);
//                    break;
//
//                case "edit":
//                    Edit(request, response);
//                    break;
//
//                case "delete":
//                    delete(request,response);
//
            default:
                showAllBook(request, response);
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
