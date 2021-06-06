package model;

public class TransactionStatus {
    int id;
    String name;
    public TransactionStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public TransactionStatus(String name) {
        this.name = name;
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
}
