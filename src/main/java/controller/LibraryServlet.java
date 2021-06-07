package controller;

import model.*;
import service.*;

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
    private CustomerManagement customerManagement;
    private BookManagement bookManagement;
    private TransactionStateManagement transactionStateManagement;
    private DetailtransactionManagement detailtransactionManagement;
    public void init() {
        userManagement = new UserManagement();
        roleManagement = new RoleManagement();
        customerManagement = new CustomerManagement();
        bookManagement = new BookManagement();
        transactionStateManagement= new TransactionStateManagement();
        detailtransactionManagement = new DetailtransactionManagement();
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
                case "createcustomer":
                    showNewCustomer(request,response);
                    break;
                case "updatein4customer":
                    showEditCustomerForm(request,response);
                    break;
                case "userlist":
                    showUserList(request,response);
                    break;
                case "setcustomer":
                    setCustomer(request,response);
                    break;
                case "createticket":
                    showNewTicket(request,response);
                    break;
                case "search":
                    searchCustomer(request,response);
                    break;
                case "viewtickets":
                    showAllTickets(request,response);
                    break;
                case "deletecustomer":
                    DeleteCustomer(request,response);
                    break;
                case "updatestatus":
                    ShowUpdateForm(request,response);
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
                case "createcustomer":
                    CreateCustomer(request, response);
                    break;
                case "updatein4customer":
                    UpdateCustomer(request,response);
                    break;
                case "createticket":
                    CreateTicket(request,response);
                    break;
                case "updatestatus":
                    UpdateTicket(request,response);
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        if (user!=null && validate.isvalidaccount(userName) && validate.isvalidaccount(pass)){
            request.setAttribute("user", user);
            RequestDispatcher dispatcher;
            if(user.getRole().getId()==1){
                List<User> userList = userManagement.selectAllUsers();
                request.setAttribute("listUser",userList);
                dispatcher = request.getRequestDispatcher("user/adminhomepage.jsp");
            }else if (user.getRole().getId()==2){
                List<Customer> customerList = customerManagement.selectAllCustomers();
                request.setAttribute("listCustomer",customerList);
                dispatcher = request.getRequestDispatcher("user/librarianhomepage.jsp");
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
    private void showNewCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/createcustomer.jsp");
        dispatcher.forward(request, response);
    }
    private void CreateCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String img = request.getParameter("img");
        User user = new User(name,birthday,email,phone,img);
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/createcustomer.jsp");
            request.setAttribute("user", user);
            dispatcher.forward(request, response);
        }else {
            RandomString randomString = new RandomString();
            String identity = randomString.getAlphaNumericString();
            customerManagement.insertCustomer(user,identity);
            List<Customer> customerList = customerManagement.selectAllCustomers();
            request.setAttribute("listCustomer",customerList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/librarianhomepage.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void showEditCustomerForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userManagement.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/librarianupdatein4.jsp");
        request.setAttribute("user", user);
        dispatcher.forward(request, response);
    }
    private void UpdateCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/librarianupdatein4.jsp");
            request.setAttribute("user", falseuser);
            dispatcher.forward(request, response);
        }else {
            userManagement.updateUser(id,name,birth,phone,img);
            User trueuser = userManagement.selectUser(id);
            request.setAttribute("user", trueuser);
            request.setAttribute("message", "Information has been updated");
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/librarianupdatein4.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void showUserList(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<User> userList = userManagement.selectAllUsers();
        List<Customer> customerList = customerManagement.selectAllCustomers();
        for (User user: userList) {
            for (Customer customer: customerList) {
                if (user.getId()==customer.getUser().getId()){
                    user.setCustomer(customer);
                }
            }
        }
        request.setAttribute("listUser",userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/listuser.jsp");
        dispatcher.forward(request, response);
    }
    private void setCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userManagement.selectUser(id);
        RandomString randomString = new RandomString();
        String identity = randomString.getAlphaNumericString();
        customerManagement.setCustomer(user,identity);
        showUserList(request,response);
    }
    private void showNewTicket(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        List<Book> bookList = bookManagement.selectAvailableBooks();
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/createticket.jsp");
        request.setAttribute("books",bookList);
        dispatcher.forward(request, response);
    }
    private void CreateTicket(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        String brrwdate = request.getParameter("brrwdate");
        String duedate = request.getParameter("duedate");
        String[] booksstr = request.getParameterValues("books");
        int[] books = new int[booksstr.length];
        for (int i = 0; i < booksstr.length; i++) {
            books[i] = Integer.parseInt(booksstr[i]);
        }
        Detailtransaction detailtransaction = new Detailtransaction(id, customerManagement.selectCustomer(id), brrwdate,duedate,transactionStateManagement.findStatusById(1));
        Validate validate = new Validate();
        if (!validate.isvaliddate(brrwdate)||!validate.isvaliddate(duedate)){
            if (!validate.isvaliddate(brrwdate)){
                request.setAttribute("brrwdatemessage", "Incorrect format");
            }
            if (!validate.isvaliddate(duedate)){
                request.setAttribute("duedatemessage", "Incorrect format");
            }
            List<Book> bookList = bookManagement.selectAvailableBooks();
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/createticket.jsp");

            request.setAttribute("books",bookList);
            dispatcher.forward(request, response);
        }else {
            detailtransactionManagement.createBorrowTicket(detailtransaction,books);
            List<Customer> customerList = customerManagement.selectAllCustomers();
            request.setAttribute("listCustomer",customerList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/librarianhomepage.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void searchCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        String search = request.getParameter("identity");
        List<Customer> customerList = customerManagement.selectbyIdentity(search);
        request.setAttribute("listCustomer", customerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/searchcustomer.jsp");
        dispatcher.forward(request, response);
    }
    private void showAllTickets(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Detailtransaction> detailtransactionList = detailtransactionManagement.getTicketsByCustomer(id);
        request.setAttribute("tickets",detailtransactionList);
        for (Detailtransaction ticket:detailtransactionList) {
            List<Book> fakebooklist = bookManagement.getFakeBook(ticket.getId());
            ticket.setBookList(fakebooklist);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/ticketlist.jsp");
        dispatcher.forward(request, response);
    }
    private void DeleteCustomer(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        int count=0;
        List<Detailtransaction> detailtransactionList = detailtransactionManagement.getTicketsByCustomer(id);
        if (detailtransactionList.size()==0){
            detailtransactionManagement.deleteCustomer(id);
            List<Customer> customerList = customerManagement.selectAllCustomers();
            request.setAttribute("listCustomer",customerList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/librarianhomepage.jsp");
            dispatcher.forward(request, response);
        }else {
            for (Detailtransaction detailtransaction: detailtransactionList) {
                if (detailtransaction.getTransactionStatus().getId()==1||detailtransaction.getTransactionStatus().getId()==2){
                    request.setAttribute("deletemessage", "There are tickets in use");
                    List<Customer> customerList = customerManagement.selectAllCustomers();
                    request.setAttribute("listCustomer",customerList);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("user/librarianhomepage.jsp");
                    dispatcher.forward(request, response);
                }else {
                    count = count+1;
                }
            }
            if (count==detailtransactionList.size()){
                detailtransactionManagement.deleteCustomer(id);
                List<Customer> customerList = customerManagement.selectAllCustomers();
                request.setAttribute("listCustomer",customerList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("user/librarianhomepage.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
    private void ShowUpdateForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Detailtransaction detailtransaction = detailtransactionManagement.getTicketById(id);
        List<TransactionStatus> transactionStatuses = transactionStateManagement.findAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/updateticket.jsp");
        request.setAttribute("ticket", detailtransaction);
        request.setAttribute("statuses", transactionStatuses);
        dispatcher.forward(request, response);
    }
    private void UpdateTicket(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        int statusid = Integer.parseInt(request.getParameter("statuslist"));
        detailtransactionManagement.updateTicket(statusid,id);
        Detailtransaction detailtransaction = detailtransactionManagement.getTicketById(id);
        List<Detailtransaction> detailtransactionList = detailtransactionManagement.getTicketsByCustomer(detailtransaction.getCustomer().getId());
        request.setAttribute("tickets",detailtransactionList);
        for (Detailtransaction ticket:detailtransactionList) {
            List<Book> fakebooklist = bookManagement.getFakeBook(ticket.getId());
            ticket.setBookList(fakebooklist);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/ticketlist.jsp");
        dispatcher.forward(request, response);
    }

}

