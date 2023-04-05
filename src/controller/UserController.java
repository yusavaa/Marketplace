package controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.ArrayList;

import model.user.User;

public class UserController {

    XStream xStream = new XStream(new StaxDriver());
    private ArrayList<User> userList = new ArrayList<>();

    public void addUser(String id, String username, String password) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPasswrod(password);
        user.setBalance(0);

        userList.add(user);
    }

    public int getSize() {
        return userList.size();
    }

    public User getUser(int index) {
        return userList.get(index);
    }

    public void removeUser(User user) {
        userList.remove(user);
    }

    public void clearUser() {
        userList.clear();
    }

    public String saveUser() {

        String sxml = xStream.toXML(userList);
        FileOutputStream fileOutput;
        try {

            byte[] bytes = sxml.getBytes("UTF-8");
            fileOutput = new FileOutputStream("src" + File.separator + "database" + File.separator + "User.xml");
            fileOutput.write(bytes);
            fileOutput.close();
            return "Succes save user";

        } catch (Exception e) {
            return e.getMessage();
        } 
    }
    
    public String loadUser() {

        FileInputStream fileInput;
        try {

            fileInput = new FileInputStream("src" + File.separator + "database" + File.separator + "User.xml");

            String sxml = "";
            int isi;
            char c;
            while ((isi = fileInput.read()) != -1) {
                c = (char) isi;
                sxml += c;
            }

            userList = (ArrayList<User>) xStream.fromXML(sxml);
            fileInput.close();
            return "Succes load user";

        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
