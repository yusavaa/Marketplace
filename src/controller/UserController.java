package controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.*;
import java.util.ArrayList;

import model.user.User;

public class UserController {

    private ArrayList<User> list = new ArrayList<>();

    public void addUser(String id, String username, String password) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPasswrod(password);
        user.setBalance(0);

        list.add(user);
    }

    public void removeUser(User user) {
        list.remove(user);
    }

    public void clearUser() {
        list.clear();
    }

    public String saveUser() {

        XStream xStream = new XStream(new StaxDriver());
        String sxml = xStream.toXML(list);
        FileOutputStream file = null;

        try {

            file = new FileOutputStream("src" + File.separator + "database" + File.separator + "User.xml");
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
        
        return "Succes add user";
    }

}
