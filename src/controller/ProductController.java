package controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.ArrayList;

import model.Product;

public class ProductController {

    XStream xStream = new XStream(new StaxDriver());
    private ArrayList<Product> productList = new ArrayList<>();

    public void addProduct(String id, String name, int price) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);

        productList.add(product);
    }

    public int getSize() {
        return productList.size();
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public void clearProduct() {
        productList.clear();
    }

    public String saveProduct() {

        String sxml = xStream.toXML(productList);
        FileOutputStream file = null;

        try {

            byte[] bytes = sxml.getBytes("UTF-8");
            file = new FileOutputStream("src" + File.separator + "database" + File.separator + "Product.xml");
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

        return "Success save product";
    }

    public String loadProduct() {

        FileInputStream file;
        try {

            file = new FileInputStream("src" + File.separator + "database" + File.separator + "Product.xml");

            String sxml = "";
            int isi;
            char c;
            while ((isi = file.read()) != -1) {
                c = (char) isi;
                sxml += c;
            }

            productList = (ArrayList<Product>) xStream.fromXML(sxml);
            file.close();
            return "Succes load user";

        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
