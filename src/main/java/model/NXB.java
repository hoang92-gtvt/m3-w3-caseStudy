package model;

public class NXB {
    private int id;
    private String name;

    public NXB() {
    }

    public NXB(String name) {
        this.name = name;
    }

    public NXB(int id, String name) {
        this.id = id;
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
