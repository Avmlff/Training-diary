package com.trainingdiary.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String login;
    private String password;
    private List<Training> trainings;
    private List<Note> notes; // ДОБАВИЛ

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.trainings = new ArrayList<>();
        this.notes = new ArrayList<>(); // ДОБАВИЛ
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

    public void addTraining(Training training) {
        trainings.add(training);
    }

    public void removeTraining(int index) {
        if (index >= 0 && index < trainings.size()) {
            trainings.remove(index);
        }
    }

    // ДОБАВИЛ методы для заметок
    public List<Note> getNotes() {
        return notes;
    }

    public void addNote(Note note) {
        notes.add(note);
    }
}