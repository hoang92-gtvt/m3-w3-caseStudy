package model;

import java.util.List;

public class Detailtransaction {
    int id;
    Customer customer;
    String borrowdate;
    String duedate;
    TransactionStatus transactionStatus;
    List<Book> bookList;

    public Detailtransaction(int id, Customer customer, String borrowdate, String duedate, TransactionStatus transactionStatus, List<Book> bookList) {
        this.id = id;
        this.customer = customer;
        this.borrowdate = borrowdate;
        this.duedate = duedate;
        this.transactionStatus = transactionStatus;
        this.bookList = bookList;
    }

    public Detailtransaction(int id, Customer customer, String borrowdate, String duedate, TransactionStatus transactionStatus) {
        this.id = id;
        this.customer = customer;
        this.borrowdate = borrowdate;
        this.duedate = duedate;
        this.transactionStatus = transactionStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(String borrowdate) {
        this.borrowdate = borrowdate;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }


}
