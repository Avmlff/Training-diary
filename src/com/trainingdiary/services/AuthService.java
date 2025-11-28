package com.trainingdiary.services;

import com.trainingdiary.models.User;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private List<User> users = new ArrayList<>();
    private User currentUser;

    public boolean register(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return false;
            }
        }

        User newUser = new User(login, password);
        users.add(newUser);
        return true;
    }

    public boolean login(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}