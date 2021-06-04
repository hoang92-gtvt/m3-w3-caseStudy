package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class PhieuMuon {
    private int id;
    private String identity;
    private String date;
    private  String duedate;
    private User user;
    private StatusPM statusPM;

    private ArrayList<Book> bookList;



    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public PhieuMuon() {
    }

    public PhieuMuon(int id, String identity, String date, String duedate, User user, StatusPM statusPM) {
        this.id = id;
        this.identity = identity;
        this.date = date;
        this.duedate = duedate;
        this.user = user;
        this.statusPM = statusPM;
    }

    public PhieuMuon(int id, String identity, String date, String duedate, User user, StatusPM statusPM, ArrayList<Book> bookList) {
        this.id = id;
        this.identity = identity;
        this.date = date;
        this.duedate = duedate;
        this.user = user;
        this.statusPM = statusPM;
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StatusPM getStatusPM() {
        return statusPM;
    }

    public void setStatusPM(StatusPM statusPM) {
        this.statusPM = statusPM;
    }
}
