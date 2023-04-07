package view;

import java.util.InputMismatchException;

import controller.AccountController;
import controller.ProductController;
import controller.TransactionController;
import util.InputUtil;
import util.LoopUtil;

public class MarketplaceView {

    private AccountController accountController = new AccountController();
    private ProductController productController = new ProductController();
    private TransactionController transactionController = new TransactionController();

    public void marketplace() {
        accountPage();
        homePage();
    }

    public void accountPage() {
        String action, username, password;

        while (LoopUtil.isLoop()) {
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
                    System.out.println("Choose a number from 1 to 2, or x");
                    break;
            }
        }
    }

    public void homePage() {

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
                    System.out.println(productController.showProductList());
                    System.out.println(addProduct());
                    InputUtil.resetInput();
                    break;
                case "2":
                    System.out.println(transactionController.displayShoppingCart());
                    break;
                case "3":
                    System.out.println(transactionController.checkout());
                    break;
                case "4":
                    System.out.println("IDR " + accountController.getBalance());
                    break;
                case "5":
                    System.out.println(topUpBalance());
                    InputUtil.resetInput();
                    break;
                case "x":
                    System.exit(0);
                default:
                    System.out.println("Choose a number from 1 to 5, or x");
                    break;
            }
        }
    }

    public String addProduct() {
        try {
            int index = InputUtil.inputInt("Choose product");
            return transactionController.addToShoppingCart(index);
        } catch (InputMismatchException e) {
            return "Input a product number";
        }
    }

    public String topUpBalance() {
        try {
            int balance = InputUtil.inputInt("Input Balance IDR");
            return accountController.setBalance(balance);
        } catch (InputMismatchException e) {
            return "Input must be a number";
        }
    }

}
