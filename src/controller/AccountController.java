package controller;

import java.util.HashMap;

import model.User;
import util.LoopUtil;

public class AccountController {

    private UserController userController = new UserController();
    private HashMap<String, String> account = new HashMap<>();
    static User targetUser;

    public AccountController() {
        mappingAccount();
    }

    public void mappingAccount() {
        for (User i : userController.loadUser())
            account.put(i.getUsername(), i.getPasswrod());
    }

    public User getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String username) {
        targetUser = userController.findUser(username);
    }

    public String register(String username, String password) {
        boolean registered = account.containsKey(username);

        if (username.length() < 4)
            return "minimum 4 characters username";
        if (registered)
            return "Username already exist";
        if (password.length() > 5) {
            userController.addUser(username, password);
            userController.saveUser();
            return "Register success";
        }
        return "Registration failed, minimum 6 characters password";
    }

    public String logIn(String username, String password) {
        mappingAccount();
        boolean registered = account.containsKey(username);

        if (!registered)
            return "Username not found";
        if (account.get(username).equals(password)) {
            setTargetUser(username);
            LoopUtil.setLoop(false);
            return "Login success";
        }
        return "Incorrect username or password";
    }

    public int getBalance() {
        return targetUser.getBalance();
    }

    public String setBalance(int balance) {
        if (targetUser == null)
            return "Username not found";
        if (balance > 0) {
            balance += this.targetUser.getBalance();
            targetUser.setBalance(balance);
            userController.saveUser();
            return "Top Up success";
        }
        return "Minimum Top Up is IDR 1";
    }

}
