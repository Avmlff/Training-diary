package com.trainingdiary.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String login;
    private String password;
    private List<Training> trainings;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.trainings = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Training> getTrainings() {
        return trainings;
    }
}