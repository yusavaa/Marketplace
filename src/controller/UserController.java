package controller;

import java.util.ArrayList;

import model.User;

public class UserController extends XMLController {

    private ArrayList<User> userList = new ArrayList<>();

    public UserController() {
        loadUser();
    }

    public void addUser(String username, String password) {
        User user = new User(username, password);
        userList.add(user);
    }

    public User findUser(String username) {
        for (int i = 0; i < userList.size(); i++) {
            boolean isFound = userList.get(i).getUsername().equals(username);
            if (isFound)
                return userList.get(i);
        }
        return null;
    }

    public void clearUser() {
        userList.clear();
    }

    public void saveUser() {
        saveToXML(userList, "User.xml");
    }

    public ArrayList<User> loadUser() {
        return userList = (ArrayList<User>) loadFroamXML(userList, "User.xml");
    }

}
