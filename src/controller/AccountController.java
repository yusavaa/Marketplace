package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.*;
import util.LoopUtil;

public class AccountController {

    private ProductController productController = new ProductController();
    private UserController userController = new UserController();
    
    private ArrayList<Product> shoppingCart = new ArrayList<>();
    private HashMap<String, String> account = new HashMap<>();
    private User targetUser;

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

    public String topUpBalance(int balance) {
        if (targetUser == null)
        return "Username not found";
        if (balance > 0) {
            balance += targetUser.getBalance();
            targetUser.setBalance(balance);
            userController.saveUser();
            return "Top Up success";
        }
        return "Minimum Top Up is IDR 1";
    }

    // >----- TransactionController -----<

    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    public String addToShoppingCart(int index) {
        shoppingCart.add(productController.loadProduct().get(index - 1));
        return "Success add product to cart";
    }

    public String displayShoppingCart() {
        String list = "";
        for (int i = 0; i < shoppingCart.size(); i++)
            list += (i + 1 + ". ") + shoppingCart.get(i).toString() + (" IDR " + shoppingCart.get(i).getPrice()) + "\n";
        return list;
    }

    public void clearShoppingCart() {
        shoppingCart.clear();
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Product product : shoppingCart)
            totalPrice += product.getPrice();
        return totalPrice;
    }

    public String checkout() {
        if (getShoppingCart().size() == 0)
            return "Add product to cart first";
        if (targetUser.getBalance() >= getTotalPrice()) {
            targetUser.setBalance(targetUser.getBalance() - getTotalPrice());
            userController.saveUser();
            clearShoppingCart();
            return "Checkout success";
        }
        return "Not enough balance";
    }

}
