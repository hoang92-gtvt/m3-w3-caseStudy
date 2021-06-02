package model;

import java.util.ArrayList;

public class Book {
    private  int id;
    private String name;
    private String description;
    private NXB nxb;
    ArrayList<Category> categories;

    public Book() {
    }

    public Book(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Book(int id, String name, String description, NXB nxb, ArrayList<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nxb = nxb;
        this.categories = categories;
    }

    public NXB getNxb() {
        return nxb;
    }

    public void setNxb(NXB nxb) {
        this.nxb = nxb;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
