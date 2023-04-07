package controller;

import java.util.ArrayList;

import model.Product;

public class ProductController extends XMLController {

    private ArrayList<Product> productList = new ArrayList<>();

    public ProductController() {
        loadProduct();
    }

    public boolean isRegistered(String name) {
        boolean registered = false;
        for (Product product : productList) {
            if (product.getName().equals(name)) {
                registered = true;
                break;
            }
        }
        return registered;
    }

    // admin feature not yet developed
    public String addProduct(String name, int price, int stock) {
        if (isRegistered(name))
            return "product already exist";
        Product product = new Product(name, price, stock);
        productList.add(product);
        return "Success add product";
    }

    public void clearProduct() {
        productList.clear();
    }

    public void saveProduct() {
        saveToXML(productList, "Product.xml");
    }

    public ArrayList<Product> loadProduct() {
        return productList = (ArrayList<Product>) loadFroamXML(productList, "Product.xml");
    }

    public String showProductList() {
        String list = "";
        for (int i = 0; i < productList.size(); i++)
            list += (i + 1 + ". ") + productList.get(i).toString() + (" IDR " + productList.get(i).getPrice()) + "\n";
        return list;
    }

}
