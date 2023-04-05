package view;

import controller.ProductController;
import controller.UserController;

public class MarketApp {
    public static void main(String[] args) {
        UserController userController = new UserController();
        ProductController productController = new ProductController();

        // userController.addUser("01", "Fath", "Fath");
        // userController.addUser("02", "Nafal", "Nafal");
        // System.out.println(userController.saveUser());
        // System.out.println(userController.loadUser());
        System.out.println(userController.getSize());

        // productController.addProduct("01", "Monitor", 1_600_000);
        // productController.addProduct("02", "Keyboard", 600_000);
        // productController.saveProduct();
        // System.out.println(productController.loadProduct());
        // System.out.println(productController.getSize());
    }
}
