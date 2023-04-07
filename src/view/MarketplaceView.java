package view;

import java.util.InputMismatchException;

import controller.AccountController;
import controller.ProductController;
import util.InputUtil;

public class MarketplaceView {

    private AccountController accountController = new AccountController();
    private ProductController productController = new ProductController();

    public void marketplace() {
        account();
        home();
    }

    public void account() {
        String action, username, password;

        while (accountController.isLoop()) {
            System.out.println("1. LogIn");
            System.out.println("2. Regiser");
            System.out.println("x. Quit");
            action = InputUtil.inputString("Choose action");

            switch (action) {
                case "1":
                    username = InputUtil.inputString("Username");
                    password = InputUtil.inputString("Password");
                    System.out.println(accountController.logIn(username, password));
                    break;
                case "2":
                    username = InputUtil.inputString("Username");
                    password = InputUtil.inputString("Password");
                    System.out.println(accountController.register(username, password));
                    break;
                case "x":
                    System.exit(0);
                default:
                    System.out.println("Choose 1, 2, or x");
                    break;
            }
        }
    }

    public void home() {

        while (true) {
            System.out.println("1. Add Product");
            System.out.println("2. Shopping cart");
            System.out.println("3. Checkout");
            System.out.println("4. Show Balance");
            System.out.println("5. TopUp Balance");
            System.out.println("x. Quit");
            String action = InputUtil.inputString("Choose action");

            switch (action) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    System.out.println(accountController.showCartList());
                    break;
                case "3":
                    checkout();
                    break;
                case "4":
                    System.out.println("IDR " + accountController.getBalance());
                    break;
                case "5":
                    topUpBalance();
                    break;
                case "x":
                    System.exit(0);
                default:
                    System.out.println("Choose 1, 2, 3, 4 or x");
                    break;
            }
        }
    }

    public void addProduct() {
        System.out.println(productController.showProductList());
        try {
            int product = InputUtil.inputInt("Choose product");
            System.out.println(accountController.addProductToCart(product));
        } catch (InputMismatchException e) {
            System.out.println("Input a product number");
        }
        InputUtil.resetInput();
    }

    public void checkout() {
        if (accountController.getShoppingCart().size() != 0) {
            if (accountController.getBalance() >= accountController.getTotalPrice()) {
                accountController.setBalance(accountController.getBalance() - accountController.getTotalPrice());
                accountController.clearCartList();
                System.out.println("Checkout success");
            } else {
                System.out.println("Not enough balance");
            }
        } else {
            System.out.println("Add product to cart first");
        }
    }

    public void topUpBalance() {
        try {
            int balance = InputUtil.inputInt("Input Balance IDR");
            System.out.println(accountController.topUpBalance(balance));
        } catch (InputMismatchException e) {
            System.out.println("Input must be a number");
        }
        InputUtil.resetInput();
    }

}
