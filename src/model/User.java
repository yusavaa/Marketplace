package model;

public class User {

    private String username;
    private String password;
    private int balance;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        balance = 0;
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

    public String toString() {
        return username;
    }

}
