package model;

public class Role {
    private String name;
    private int id;
    public Role(String name) {
        this.name = name;
    }
    public Role(int id,String name) {
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
