package controller;

import model.User;
import service.UserManagement;
import service.Validate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LibraryServlet", urlPatterns  = "/Libraries")
public class LibraryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserManagement userManagement;
    public void init() {
        userManagement = new UserManagement();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "signin":
                    showSignInForm(request, response);
                    break;
                default:
                case "passwordchange":
                    showPasswordForm(request, response);
                    break;
                case "updatein4":
                    showUpdateForm(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "signin":
                    getSignIn(request, response);
                    break;
                case "passwordchange":
                    ChangePassword(request, response);
                    break;
                case "updatein4":
                    UpdateIn4mation(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void showSignInForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/signin.jsp");
        dispatcher.forward(request, response);
    }
    private void getSignIn(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String userName = request.getParameter("username");
        String pass = request.getParameter("pass");
        User user = userManagement.getUserByUserNameandPass(userName,pass);
        Validate validate = new Validate();
        if (user!=null && validate.isvalid(userName) && validate.isvalid(pass)){
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/homepage.jsp");
            dispatcher.forward(request, response);
        }else if (!validate.isvalid(userName)||!validate.isvalid(pass)){
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/signin.jsp");
            request.setAttribute("message", "Incorrect format");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/signin.jsp");
            request.setAttribute("message", "Incorrect account");
            dispatcher.forward(request, response);
        }
    }
    private void showPasswordForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/changepassword.jsp");
        dispatcher.forward(request, response);
    }
    private void ChangePassword(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String oldpass = request.getParameter("oldpassword");
        String newpass = request.getParameter("newpassword");
        User user = userManagement.selectUser(id);
        Validate validate = new Validate();
        if (user!=null && oldpass.equals(user.getPass())){
            if (validate.isvalid(newpass)){
                userManagement.setPassword(id,newpass);
                RequestDispatcher dispatcher = request.getRequestDispatcher("user/changepassword.jsp");
                request.setAttribute("message", "Password has been updated");
                dispatcher.forward(request, response);
            }else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("user/changepassword.jsp");
                request.setAttribute("message", "Incorrect format");
                dispatcher.forward(request, response);
            }
        }else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/changepassword.jsp");
            request.setAttribute("message", "Incorrect password");
            dispatcher.forward(request, response);
        }
    }
    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userManagement.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/updatein4.jsp");
        request.setAttribute("user", user);
        dispatcher.forward(request, response);
    }
    private void UpdateIn4mation(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String birth = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String img = request.getParameter("urlOfImg");
        userManagement.updateUser(id,name,birth,phone,img);
        User user = userManagement.selectUser(id);
        request.setAttribute("user", user);
        request.setAttribute("message", "Information has been updated");
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/updatein4.jsp");
        dispatcher.forward(request, response);
    }
}
