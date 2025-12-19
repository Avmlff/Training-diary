package com.trainingdiary.services;

import com.trainingdiary.models.User;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private List<User> users = new ArrayList<>();
    private User currentUser;

    public AuthService() {
        User trainer = new User("тренер", "123");
        trainer.setRole("TRAINER");
        users.add(trainer);
    }

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

    public boolean registerTrainer(String login, String password, String trainerCode) {
        if (!"TRAINER123".equals(trainerCode)) {
            return false;
        }

        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return false;
            }
        }

        User newUser = new User(login, password);
        newUser.setRole("TRAINER");
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

    public List<User> getAllUsers() {
        return users;
    }
}