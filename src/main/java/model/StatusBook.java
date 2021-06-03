package model;

public class StatusBook {
    private int id;
<<<<<<< HEAD
    private String name;

    public StatusBook(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public StatusBook(String name) {
        this.name = name;
    }

    public StatusBook() {
    }

=======
    private  String name;

    public StatusBook() {
    }

    public StatusBook(int id, String name) {
        this.id = id;
        this.name = name;
    }

>>>>>>> origin/toan
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
}
