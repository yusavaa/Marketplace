// package controller.bug;

// import java.util.ArrayList;

// import model.Product;
// import model.User;

/**
 * didn't go as expected
 * 
 * this code has been merger with "AccountController" class
 */

// public class TransactionController {

//     private ArrayList<Product> shoppingCart = new ArrayList<>();
//     private ProductController productController = new ProductController();
//     private UserController userController = new UserController();
//     private User targetUser;

//     public void setTargetUser() {
//         targetUser = userController.findUser(AccountController.username);
//     }

//     public ArrayList<Product> getShoppingCart() {
//         return shoppingCart;
//     }

//     public String addToShoppingCart(int index) {
//         shoppingCart.add(productController.loadProduct().get(index - 1));
//         return "Success add product to cart";
//     }

//     public String displayShoppingCart() {
//         String list = "";
//         for (int i = 0; i < shoppingCart.size(); i++)
//             list += (i + 1 + ". ") + shoppingCart.get(i).toString() + (" IDR " + shoppingCart.get(i).getPrice()) + "\n";
//         return list;
//     }

//     public void clearShoppingCart() {
//         shoppingCart.clear();
//     }

//     public int getTotalPrice() {
//         int totalPrice = 0;
//         for (Product product : shoppingCart)
//             totalPrice += product.getPrice();
//         return totalPrice;
//     }

//     public String checkout() {
//         if (getShoppingCart().size() == 0)
//             return "Add product to cart first";
//         if (userController.findUser(AccountController.username).getBalance() >= getTotalPrice()) {
//             userController.findUser(AccountController.username).setBalance(targetUser.getBalance() - getTotalPrice());
//             userController.saveUser();
//             clearShoppingCart();
//             return "Checkout success";
//         }
//         return "Not enough balance";
//     }

// }
