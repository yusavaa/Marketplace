package model.user;

public class User {

    private String id;
    private String username;
    private String password;
    private int balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswrod() {
        return password;
    }

    public void setPasswrod(String passwrod) {
        this.password = passwrod;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
