package model;

public class Book {
    int id;
    String name;
    String description;
    int status_id;
    int nxb_id;
    public Book(int id, String name, String description, int status_id, int nxb_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status_id = status_id;
        this.nxb_id = nxb_id;
    }
    public Book(String name, String description, int status_id, int nxb_id) {
        this.name = name;
        this.description = description;
        this.status_id = status_id;
        this.nxb_id = nxb_id;
    }
    public Book() {
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

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getNxb_id() {
        return nxb_id;
    }

    public void setNxb_id(int nxb_id) {
        this.nxb_id = nxb_id;
    }
}
