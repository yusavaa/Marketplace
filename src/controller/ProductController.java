package controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.*;
import java.util.ArrayList;

import model.Product;

public class ProductController {

    private ArrayList<Product> list = new ArrayList<>();

    public void addProduct(String id, String name, int price) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
    }

    public void removeProduct(Product product) {
        list.remove(product);
    }

    public void clearProduct() {
        list.clear();
    }

    public String saveProduct() {

        XStream xStream = new XStream(new StaxDriver());
        String sxml = xStream.toXML(list);
        FileOutputStream file = null;

        try {

            file = new FileOutputStream("src"+ File.separator +"database"+ File.separator +"Product.xml");
            byte[] bytes = sxml.getBytes("UTF-8");
            file.write(bytes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            if (file != null) {
                try {
                    file.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        return "Success add product";
    }

}
