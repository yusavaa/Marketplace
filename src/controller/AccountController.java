package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Product;
import model.User;
import util.LoopUtil;

public class AccountController extends LoopUtil {

    private String logInAccount;
    private HashMap<String, String> account = new HashMap<>();
    private ArrayList<Product> shoppingCart = new ArrayList<>();

    private ProductController productController = new ProductController();
    private UserController userController = new UserController();

    public AccountController() {
        mappingAccount();
    }

    public void mappingAccount() {
        for (User i : userController.loadUser())
            account.put(i.getUsername(), i.getPasswrod());
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
            logInAccount = username;
            setLoop(false);
            return "Login success";
        }
        return "Incorrect username or password";
    }

    public String topUpBalance(int balance) {
        if (userController.findUser(logInAccount) == null)
            return "Username not found";
        if (balance > 0) {
            balance += userController.findUser(logInAccount).getBalance();
            userController.findUser(logInAccount).setBalance(balance);
            userController.saveUser();
            return "Top Up success";
        }
        return "Minimum Top Up is IDR 1";
    }

    public String addProductToCart(int index) {
        shoppingCart.add(productController.loadProduct().get(index - 1));
        return "Success add product to cart";
    }

    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    public String showCartList() {
        String list = "";
        for (int i = 0; i < shoppingCart.size(); i++)
            list += (i + 1 + ". ") + shoppingCart.get(i).toString() + (" IDR " + shoppingCart.get(i).getPrice()) + "\n";
        return list;
    }

    public void clearCartList() {
        shoppingCart.clear();
    }

    public int getBalance() {
        return userController.findUser(logInAccount).getBalance();
    }

    public void setBalance(int balance) {
        userController.findUser(logInAccount).setBalance(balance);
        userController.saveUser();
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Product product : shoppingCart)
            totalPrice += product.getPrice();
        return totalPrice;
    }

}
