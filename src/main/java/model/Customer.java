package model;

public class Customer {
    int id;
    String identity;
    User user;
    public Customer(int id, String identity, User user) {
        this.id = id;
        this.identity = identity;
        this.user = user;
    }
    public Customer(String identity, User user) {
        this.identity = identity;
        this.user = user;
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}

