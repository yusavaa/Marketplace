package view;

import controller.ProductController;
import controller.UserController;

public class MarketApp {
    public static void main(String[] args) {
        UserController userController = new UserController();
        ProductController productController = new ProductController();

        userController.addUser("01", "Fath", "Fath");
        userController.addUser("02", "Mimik", "Mimik");
        userController.saveUser();

        productController.saveProduct();
    }
}
