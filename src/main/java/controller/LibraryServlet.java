package controller;

import model.Role;
import model.User;
import service.RoleManagement;
import service.UserManagement;
import service.Validate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LibraryServlet", urlPatterns  = "/Libraries")
public class LibraryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserManagement userManagement;
    private RoleManagement roleManagement;
    public void init() {
        userManagement = new UserManagement();
        roleManagement = new RoleManagement();
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
                case "create":
                    showNewUserForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteUser(request,response);
                    break;
                case "restorepage":
                    showDeletedUsers(request,response);
                    break;
                case "restore":
                    restoreDeletedUser(request,response);
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
                case "create":
                    CreateUser(request, response);
                    break;
                case "edit":
                    UpdateUser(request, response);
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
        List<User> userList = userManagement.selectAllUsers();
        Validate validate = new Validate();
        if (user!=null && validate.isvalidaccount(userName) && validate.isvalidaccount(pass)){
            request.setAttribute("user", user);
            request.setAttribute("listUser",userList);
            RequestDispatcher dispatcher;
            if(user.getRole().getId()==1){
                dispatcher = request.getRequestDispatcher("user/adminhomepage.jsp");
            }else if (user.getRole().getId()==2){
                dispatcher = request.getRequestDispatcher("user/adminhomepage.jsp");
            }else {
                dispatcher = request.getRequestDispatcher("user/homepage.jsp");
            }
            dispatcher.forward(request, response);
        }else if (!validate.isvalidaccount(userName)||!validate.isvalidaccount(pass)){
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
            if (validate.isvalidaccount(newpass)){
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
        User user = userManagement.selectUser(id);
        Validate validate = new Validate();
        if (name.equals("")||!validate.isvaliddate(birth)||!validate.isvalidphone(phone)){
            User falseuser = new User(id,name,birth,user.getEmail(),phone,img,user.getRole());
            if (name.equals("")){
                request.setAttribute("namemessage", "Incorrect format");
            }
            if (!validate.isvaliddate(birth)){
                request.setAttribute("birthdaymessage", "Incorrect format");
            }
            if (!validate.isvalidphone(phone)){
                request.setAttribute("phonemessage", "Incorrect format");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/updatein4.jsp");
            request.setAttribute("user", falseuser);
            dispatcher.forward(request, response);
        }else {
            userManagement.updateUser(id,name,birth,phone,img);
            User trueuser = userManagement.selectUser(id);
            request.setAttribute("user", trueuser);
            request.setAttribute("message", "Information has been updated");
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/updatein4.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void showNewUserForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        List<Role> roleList = roleManagement.findAll();
        request.setAttribute("roles",roleList);
        dispatcher.forward(request, response);
    }
    private void CreateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String img = request.getParameter("img");
        String userName = request.getParameter("userName");
        String pass = request.getParameter("Pass");
        int roleid = Integer.parseInt(request.getParameter("role"));
        Role role = roleManagement.findRoleById(roleid);
        User user = new User(name,birthday,email,phone,img,userName,pass,role);
        Validate validate = new Validate();
        if (name.equals("")||!validate.isvaliddate(birthday)||!validate.isvalidemail(email)||!validate.isvalidphone(phone)||!validate.isvalidaccount(userName)||!validate.isvalidaccount(pass)) {
            if (name.equals("")) {
                request.setAttribute("namemessage", "Incorrect format");
            }
            if (!validate.isvaliddate(birthday)) {
                request.setAttribute("birthdaymessage", "Incorrect format");
            }
            if (!validate.isvalidemail(email)) {
                request.setAttribute("emailmessage", "Incorrect format");
            }
            if (!validate.isvalidphone(phone)) {
                request.setAttribute("phonemessage", "Incorrect format");
            }
            if (!validate.isvalidaccount(userName)){
                request.setAttribute("userNamemessage", "Incorrect format");
            }
            if (!validate.isvalidaccount(pass)){
                request.setAttribute("passmessage", "Incorrect format");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
            List<Role> roleList = roleManagement.findAll();
            request.setAttribute("user", user);
            request.setAttribute("roles",roleList);
            dispatcher.forward(request, response);
        }else{
            userManagement.insertUser(user);
            List<User> userList = userManagement.selectAllUsers();
            request.setAttribute("listUser",userList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/adminhomepage.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userManagement.selectUser(id);
        List<Role> roles = roleManagement.findAll();
        List<Role> roleList = new ArrayList<>();
        for (Role role:roles) {
            if (role.getId()==user.getRole().getId()){
                continue;
            }
            roleList.add(role);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/adminupdatein4.jsp");
        request.setAttribute("roles",roleList);
        request.setAttribute("user", user);
        dispatcher.forward(request, response);
    }
    private void UpdateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String img = request.getParameter("urlOfImg");
        int roleid = Integer.parseInt(request.getParameter("role"));
        Role role = roleManagement.findRoleById(roleid);
        User user = new User(id,name,birthday,email,phone,img,role);
        System.out.println(user.getId());
        Validate validate = new Validate();
        if (name.equals("")||!validate.isvaliddate(birthday)||!validate.isvalidemail(email)||!validate.isvalidphone(phone)){
            if (name.equals("")){
                request.setAttribute("namemessage", "Incorrect format");
            }
            if (!validate.isvaliddate(birthday)){
                request.setAttribute("birthdaymessage", "Incorrect format");
            }
            if (!validate.isvalidemail(email)){
                request.setAttribute("emailmessage", "Incorrect format");
            }
            if (!validate.isvalidphone(phone)){
                request.setAttribute("phonemessage", "Incorrect format");
            }
            List<Role> roles = roleManagement.findAll();
            List<Role> roleList = new ArrayList<>();
            for (Role role1:roles) {
                if (role1.getId()==user.getRole().getId()){
                    continue;
                }
                roleList.add(role1);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/adminupdatein4.jsp");
            request.setAttribute("roles",roleList);
            request.setAttribute("user", user);
            dispatcher.forward(request, response);
        }else {
            userManagement.updateUserIn4(user);
            List<User> userList = userManagement.selectAllUsers();
            request.setAttribute("listUser",userList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/adminhomepage.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userManagement.selectUser(id);
        userManagement.deleteUser(user);
        List<User> userList = userManagement.selectAllUsers();
        request.setAttribute("listUser",userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/adminhomepage.jsp");
        dispatcher.forward(request, response);
    }
    private void showDeletedUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        List<User> userList = userManagement.selectAllDeletedUsers();
        request.setAttribute("listUser", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/deletedusers.jsp");
        dispatcher.forward(request, response);
    }
    private void restoreDeletedUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userManagement.selecDeletedtUser(id);
        userManagement.insertUser(user);
        userManagement.deletedeletedUser(user);
        List<User> userList = userManagement.selectAllUsers();
        request.setAttribute("listUser",userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/adminhomepage.jsp");
        dispatcher.forward(request, response);
    }

}
